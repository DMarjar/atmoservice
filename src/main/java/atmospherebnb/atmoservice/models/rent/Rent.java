package atmospherebnb.atmoservice.models.rent;

import atmospherebnb.atmoservice.models.department.Department;
import atmospherebnb.atmoservice.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "rents")
@Entity
@NoArgsConstructor
public class Rent {
    // Rent id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Rent date
    @Column(name = "date")
    private String date;
    // ManyToOne relation with Department
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;
    // ManyToOne relation with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

public Rent(Long id, String date) {
        this.id = id;
        this.date = date;
    }
}