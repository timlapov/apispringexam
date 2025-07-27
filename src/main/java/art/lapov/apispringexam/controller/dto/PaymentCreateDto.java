package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for creating a new payment.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreateDto {
    private String fromUserId;
    private String toUserId;
    private Double amount;
    private String eventId;
}
