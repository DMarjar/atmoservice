package atmospherebnb.atmoservice.models.department;

import atmospherebnb.atmoservice.models.rent.Rent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "departments")
@Entity
@NoArgsConstructor
public class Department {
    // Department id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Department name
    @Column(name = "name", length = 90, nullable = false)
    private String name;
    // Department location
    @Column(name = "location", columnDefinition = "json", nullable = false)
    private String location;
    // Department images
    @Column(name = "images", columnDefinition = "json")
    private String images;
    // Department description
    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;
    // Department rating
    @Column(name = "rating")
    private double rating;
    // Department total rating
    @Column(name = "total_rating")
    private int totalRating;
    // Department price
    @Column(name = "price", nullable = false)
    private double price;
    // OneToMany relation with Rent
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rent> rent;

    public Department(Long id, String name, String location, String images, String description, double rating, int totalRating, double price, List<Rent> rent) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.images = images;
        this.description = description;
        this.rating = rating;
        this.totalRating = totalRating;
        this.price = price;
        this.rent = rent;
    }
}
