package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for representing a debt between two users.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebtDto {
    private String fromUser;
    private String toUser;
    private Double amount;
}
