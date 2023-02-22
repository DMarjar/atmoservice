package atmospherebnb.atmoservice.controllers.user;

import atmospherebnb.atmoservice.models.user.User;
import atmospherebnb.atmoservice.services.user.UserService;
import atmospherebnb.atmoservice.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atmosphere/user")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    private UserService service;

    // GET
    // URL: http://localhost:8080/atmosphere/user/
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK);
    }

    // GET ONE
    // URL: http://localhost:8080/atmosphere/user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK);
    }

    // POST
    // URL: http://localhost:8080/atmosphere/user/
    @PostMapping("/")
    public ResponseEntity<CustomResponse<User>> insert(@RequestBody UserDto user, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while inserting user"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.insert(user.castToUser()),
                HttpStatus.CREATED);
    }

    // PUT
    // URL: http://localhost:8080/atmosphere/user/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> update(@PathVariable("id") Long id, @RequestBody UserDto user, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "Error while updating user"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                this.service.update(user.castToUser()),
                HttpStatus.OK);
    }

    // DELETE
    // URL: http://localhost:8080/atmosphere/user/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.delete(id),
                HttpStatus.OK);
    }
}
