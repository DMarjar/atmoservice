package atmospherebnb.atmoservice.models.person;

import atmospherebnb.atmoservice.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Person id
    private Long id;
    // Person full name
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;
    // Person birthdate
    @Column(nullable = false, length = 50)
    private String birthdate;
    // Person user
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, optional = false)
    private User user;

    public Person(Long id, String fullName, String birthdate) {
        this.id = id;
        this.fullName = fullName;
        this.birthdate = birthdate;
    }
}
