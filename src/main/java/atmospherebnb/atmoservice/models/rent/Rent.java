package atmospherebnb.atmoservice.models.rent;

import atmospherebnb.atmoservice.models.department.Department;
import atmospherebnb.atmoservice.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "date", nullable = false)
    private String date;
    // ManyToOne relation with Department
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private Department department;
    // ManyToOne relation with User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Rent(Long id, String date, Department department, User user) {
        this.id = id;
        this.date = date;
        this.department = department;
        this.user = user;
    }
}