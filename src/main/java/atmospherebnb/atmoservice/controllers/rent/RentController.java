package atmospherebnb.atmoservice.controllers.rent;

import atmospherebnb.atmoservice.controllers.person.PersonDto;
import atmospherebnb.atmoservice.models.person.Person;
import atmospherebnb.atmoservice.models.rent.Rent;
import atmospherebnb.atmoservice.services.rent.RentService;
import atmospherebnb.atmoservice.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atmosphere/rent")
@CrossOrigin(origins = {"*"})
public class RentController {
    @Autowired
    private RentService service;

    // GET
    // URL: http://localhost:8080/atmosphere/rent/
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Rent>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK);
    }

    // GET ONE
    // URL: http://localhost:8080/atmosphere/rent/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Rent>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK);
    }

    // POST
    // URL: http://localhost:8080/atmosphere/rent/
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Rent>> insert(@RequestBody RentDto rent, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while inserting rent"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.insert(rent.castToRent()),
                HttpStatus.CREATED);
    }

    // PUT
    // URL: http://localhost:8080/atmosphere/rent/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Rent>> update(@PathVariable("id") Long id, @RequestBody RentDto rent, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while updating rent"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.update(rent.castToRent()),
                HttpStatus.OK);
    }

    // DELETE
    // URL: http://localhost:8080/atmosphere/rent/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id),
                HttpStatus.OK);
    }
}
