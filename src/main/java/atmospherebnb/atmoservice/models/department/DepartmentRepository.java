package atmospherebnb.atmoservice.models.department;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Find a department by id
    Optional<Department> findById(long id);
    // Find a department by name
    Optional<Department> findByName(String name);
}
