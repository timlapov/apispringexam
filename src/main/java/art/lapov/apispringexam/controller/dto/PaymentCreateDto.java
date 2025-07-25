package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreateDto {
    private String fromUserId;
    private String toUserId;
    private Double amount;
    private String eventId;
}
