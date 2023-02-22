package atmospherebnb.atmoservice.models.person;

import atmospherebnb.atmoservice.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // Find a person by id
    Optional<Person> findById(long id);
    // Find a person by full name
    Optional<Person> findByFullName(String fullName);
    // Find a person by its user
    Optional<Person> findByUser(User user);
}
