package atmospherebnb.atmoservice.services.department;

import atmospherebnb.atmoservice.models.department.Department;
import atmospherebnb.atmoservice.models.department.DepartmentRepository;
import atmospherebnb.atmoservice.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    // Get all departments
    @Transactional(readOnly = true)
    public CustomResponse<List<Department>> getAll() {
        return new CustomResponse<>(this.repository.findAll(), false, 200, "OK");
    }

    // Get one department
    @Transactional(readOnly = true)
    public CustomResponse<Department> getOne(long id) {
        return new CustomResponse<>(this.repository.findById(id).get(), false, 200, "OK");
    }

    // Insert a department
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Department> insert(Department department) {
        return departmentExists(department);
    }

    // Update a department
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Department> update(Department department) {
        return departmentExists(department);
    }

    // Delete a department
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(long id) {
        Optional<Department> department = this.repository.findById(id);
        if (department.isPresent()) {
            this.repository.delete(department.get());
            return new CustomResponse<>(null, false, 200, "Department deleted correctly!");
        }
        return new CustomResponse<>(null, true, 400, "The department does not exist");
    }

    // Method for checking if a department exists
    private CustomResponse<Department> departmentExists(Department department) {
        Optional<Department> departmentExists = this.repository.findById(department.getId());
        if (departmentExists.isPresent()) {
            return new CustomResponse<>(null, true, 400, "The department already exists");
        }
        return new CustomResponse<>(this.repository.save(department), false, 200, "OK");
    }
}
