package atmospherebnb.atmoservice.models.department;

import atmospherebnb.atmoservice.models.rent.Rent;
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
    @Column(name = "name", length = 90)
    private String name;
    // Department location
    @Column(name = "location", columnDefinition = "json")
    private String location;
    // Department images
    @Column(name = "images", columnDefinition = "json")
    private String images;
    // Department description
    @Column(name = "description", columnDefinition = "text")
    private String description;
    // Department rating
    @Column(name = "rating")
    private double rating;
    // Department total rating
    @Column(name = "total_rating")
    private int totalRating;
    // Department price
    @Column(name = "price")
    private double price;
    // OneToMany relation with Rent
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Rent> rent;

}
