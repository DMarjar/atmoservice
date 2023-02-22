package atmospherebnb.atmoservice.controllers.person;

import atmospherebnb.atmoservice.models.person.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class PersonDto {
    private long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 150)
    private String fullName;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 150)
    private String birthdate;

    public Person castToPerson() {
        return new Person(
                this.id,
                this.fullName,
                this.birthdate
        );
    }
}
