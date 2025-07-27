package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO for representing the balance of an event.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private List<DebtDto> debts;
    private Double totalExpenses;
}
