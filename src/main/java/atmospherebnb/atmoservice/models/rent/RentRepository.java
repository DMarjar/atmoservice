package atmospherebnb.atmoservice.models.rent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long>{
    // Find a rent by id
    Rent findById(long id);
}
