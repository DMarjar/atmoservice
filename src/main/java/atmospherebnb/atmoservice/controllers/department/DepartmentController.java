package atmospherebnb.atmoservice.controllers.department;

import atmospherebnb.atmoservice.controllers.person.PersonDto;
import atmospherebnb.atmoservice.models.department.Department;
import atmospherebnb.atmoservice.models.person.Person;
import atmospherebnb.atmoservice.services.department.DepartmentService;
import atmospherebnb.atmoservice.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atmosphere/department")
@CrossOrigin(origins = {"*"})
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    // GET
    // URL: http://localhost:8080/atmosphere/department/
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Department>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK);
    }

    // GET ONE
    // URL: http://localhost:8080/atmosphere/department/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Department>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK);
    }

    // POST
    // URL: http://localhost:8080/atmosphere/department/
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Department>> insert(@RequestBody DepartmentDto department, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while inserting department"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.insert(department.castToDepartment()),
                HttpStatus.CREATED);
    }

    // PUT
    // URL: http://localhost:8080/atmosphere/department/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Department>> update(@PathVariable("id") Long id, @RequestBody DepartmentDto department, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while updating department"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.update(department.castToDepartment()),
                HttpStatus.OK);
    }

    // DELETE
    // URL: http://localhost:8080/atmosphere/department/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id),
                HttpStatus.OK);
    }
}
