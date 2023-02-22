package atmospherebnb.atmoservice.services.user;

import atmospherebnb.atmoservice.models.user.User;
import atmospherebnb.atmoservice.models.user.UserRepository;
import atmospherebnb.atmoservice.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;

    // Get all users
    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getAll() {
        return new CustomResponse<>(this.repository.findAll(), false, 200, "OK");
    }

    // Get one user
    @Transactional(readOnly = true)
    public CustomResponse<User> getOne(long id) {
        return new CustomResponse<>(this.repository.findById(id).get(), false, 200, "OK");
    }

    // Insert a user
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> insert(User user) {
        return userExists(user);
    }

    // Update a user
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> update(User user) {
        return userExists(user);
    }

    // Delete a user
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(long id) {
        Optional<User> user = this.repository.findById(id);
        if (user.isPresent()) {
            this.repository.delete(user.get());
            return new CustomResponse<>(null, false, 200, "User deleted correctly!");
        }
        return new CustomResponse<>(null, true, 400, "The user does not exist");
    }

    // Method for checking if a user exists
    private CustomResponse<User> userExists(User user) {
        Optional<User> userExists = this.repository.findByEmail(user.getEmail().trim());
        if (userExists.isPresent()) {
            return new CustomResponse<>(null, true, 400, "The user already exists");
        }
        return new CustomResponse<>(this.repository.save(user), false, 200, "OK");
    }
}
