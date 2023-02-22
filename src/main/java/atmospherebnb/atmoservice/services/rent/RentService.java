package atmospherebnb.atmoservice.services.rent;

import atmospherebnb.atmoservice.models.rent.Rent;
import atmospherebnb.atmoservice.models.rent.RentRepository;
import atmospherebnb.atmoservice.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentService {
    @Autowired
    private RentRepository repository;

    // Get all rents
    @Transactional(readOnly = true)
    public CustomResponse<List<Rent>> getAll() {
        return new CustomResponse<>(this.repository.findAll(), false, 200, "OK");
    }

    // Get one rent
    @Transactional(readOnly = true)
    public CustomResponse<Rent> getOne(long id) {
        return new CustomResponse<>(this.repository.findById(id).get(), false, 200, "OK");
    }

    // Insert a rent
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Rent> insert(Rent rent) {
        return rentExists(rent);
    }

    // Update a rent
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Rent> update(Rent rent) {
        return rentExists(rent);
    }

    // Delete a rent
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(long id) {
        Optional<Rent> rent = this.repository.findById(id);
        if (rent.isPresent()) {
            this.repository.delete(rent.get());
            return new CustomResponse<>(null, false, 200, "Rent deleted correctly!");
        }
        return new CustomResponse<>(null, true, 400, "The rent does not exist");
    }

    // Method for checking if a rent exists
    private CustomResponse<Rent> rentExists(Rent rent) {
        Optional<Rent> rentExists = this.repository.findById(rent.getId());
        if (rentExists.isPresent()) {
            return new CustomResponse<>(null, true, 400, "The rent already exists");
        }
        return new CustomResponse<>(null, false, 200, "OK");
    }
}
