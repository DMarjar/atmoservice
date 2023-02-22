package atmospherebnb.atmoservice.controllers.user;

import atmospherebnb.atmoservice.models.person.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 150)
    private String email;
    @NotNull
    @NotBlank
    private Person person;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 150)
    private String uid;

}
