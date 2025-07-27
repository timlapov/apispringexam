package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO for creating a new expense.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCreateDto {
    private String description;
    private Double amount;
    private String payerId;
}
