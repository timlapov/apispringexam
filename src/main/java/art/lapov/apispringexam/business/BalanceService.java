package art.lapov.apispringexam.business;

import art.lapov.apispringexam.controller.dto.DebtDto;
import art.lapov.apispringexam.entity.Expense;
import art.lapov.apispringexam.entity.Payment;
import art.lapov.apispringexam.entity.User;

import java.util.List;

public interface BalanceService {
    List<DebtDto> calculate(List<User> participants, List<Expense> expenses, List<Payment> payments);
}
