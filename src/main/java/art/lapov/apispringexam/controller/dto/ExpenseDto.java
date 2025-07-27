package art.lapov.apispringexam.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for {@link art.lapov.apispringexam.entity.Expense}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    @Positive
    private Double amount;
    @NotBlank
    private String description;
    private LocalDateTime createdAt;
    @NotBlank
    private String payerId;
    @NotBlank
    private String eventId;
}
