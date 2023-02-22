package atmospherebnb.atmoservice.controllers.department;

import atmospherebnb.atmoservice.models.department.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 90)
    private String name;
    @NotNull
    @NotBlank
    private String location;
    private String images;
    @NotNull
    @NotBlank
    private String description;
    private double rating;
    private int totalRating;
    @NotNull
    @NotBlank
    private double price;

    public Department castToDepartment() {
        return new Department(
                this.id,
                this.name,
                this.location,
                this.images,
                this.description,
                this.rating,
                this.totalRating,
                this.price,
                null
        );
    }
}
