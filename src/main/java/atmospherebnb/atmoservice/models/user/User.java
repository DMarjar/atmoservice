package atmospherebnb.atmoservice.models.user;

import atmospherebnb.atmoservice.models.person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
    // User id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // User email
    @Column(nullable = false, length = 100)
    private String email;
    // User person
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person person;
    // User uid
    @Column(nullable = false)
    private String uid;

    public User(long id, String email, Person person, String uid) {
        this.id = id;
        this.email = email;
        this.person = person;
        this.uid = uid;
    }
}
