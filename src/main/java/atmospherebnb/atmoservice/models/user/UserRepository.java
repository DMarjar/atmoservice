package atmospherebnb.atmoservice.models.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by id
    Optional<User> findById(long id);
    // Find a user by email
    Optional<User> findByEmail(String email);
    // Find a user by uid
    Optional<User> findByUid(String uid);
    // Find a user by person id
    Optional<User> findByPersonId(long personId);
}
