package atmospherebnb.atmoservice.models.rent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long>{
    // Find a rent by id
    Optional<Rent> findById(long id);
}
