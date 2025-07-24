package art.lapov.apispringexam.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    @NotNull
    @Positive
    private Double amount;
    private LocalDateTime paidAt;
    @NotBlank
    private String userId;
    @NotBlank
    private String eventId;
}
