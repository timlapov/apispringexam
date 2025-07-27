package art.lapov.apispringexam.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for creating a new event.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateDto {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String ownerId;
}
