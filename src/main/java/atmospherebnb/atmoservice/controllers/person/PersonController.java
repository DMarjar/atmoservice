package atmospherebnb.atmoservice.controllers.person;

import atmospherebnb.atmoservice.models.person.Person;
import atmospherebnb.atmoservice.services.person.PersonService;
import atmospherebnb.atmoservice.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atmosphere/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
    @Autowired
    private PersonService service;

    // GET
    // URL: http://localhost:8080/atmosphere/person/
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Person>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK);
    }

    // GET ONE
    // URL: http://localhost:8080/atmosphere/person/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Person>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK);
    }

    // POST
    // URL: http://localhost:8080/atmosphere/person/
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Person>> insert(@RequestBody PersonDto person, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while inserting person"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.insert(person.castToPerson()),
                HttpStatus.CREATED);
    }

    // PUT
    // URL: http://localhost:8080/atmosphere/person/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Person>> update(@PathVariable("id") Long id, @RequestBody PersonDto person, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while updating person"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.update(person.castToPerson()),
                HttpStatus.OK);
    }

    // DELETE
    // URL: http://localhost:8080/atmosphere/person/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id),
                HttpStatus.OK);
    }
}
