package atmospherebnb.atmoservice.services.person;

import atmospherebnb.atmoservice.models.person.Person;
import atmospherebnb.atmoservice.models.person.PersonRepository;
import atmospherebnb.atmoservice.utils.CustomResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository repository;

    // Get all persons
    @Transactional(readOnly = true)
    public CustomResponse<List<Person>> getAll() {
        return new CustomResponse<>(this.repository.findAll(), false, 200, "OK");
    }

    // Get one person
    @Transactional(readOnly = true)
    public CustomResponse<Person> getOne(long id) {
        return new CustomResponse<>(this.repository.findById(id).get(), false, 200, "OK");
    }

    // Insert a person
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Person> insert(Person person) {
        return personExists(person);
    }

    // Update a person
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Person> update(Person person) {
        return personExists(person);
    }

    // Delete a person
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(long id) {
        Optional<Person> person = this.repository.findById(id);
        if (person.isPresent()) {
            this.repository.delete(person.get());
            return new CustomResponse<>(null, false, 200, "Person deleted correctly!");
        }
        return new CustomResponse<>(null, true, 400, "The person does not exist");
    }

    // Method for checking if a person exists
    private CustomResponse<Person> personExists(Person person) {
        Optional<Person> personExists = this.repository.findByFullName(person.getFullName().trim());
        if (personExists.isPresent()) {
            return new CustomResponse<>(null, true, 400, "The person already exists");
        }
        return new CustomResponse<>(this.repository.saveAndFlush(person), false, 200, "Person registered correctly!");
    }
}
