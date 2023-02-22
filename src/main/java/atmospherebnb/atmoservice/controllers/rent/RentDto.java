package atmospherebnb.atmoservice.controllers.rent;

import atmospherebnb.atmoservice.models.department.Department;
import atmospherebnb.atmoservice.models.rent.Rent;
import atmospherebnb.atmoservice.models.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class RentDto {
    private long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 150)
    private String date;
    @NotNull
    @NotBlank
    private Department department;
    @NotNull
    @NotBlank
    private User user;

    public Rent castToRent() {
        return new Rent(
                this.id,
                this.date,
                this.department,
                this.user
        );
    }
}
