package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for {@link art.lapov.apispringexam.entity.Payment}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String id;
    private Double amount;
    private LocalDateTime createdAt;
    private String fromUserId;
    private String toUserId;
    private String eventId;
}
