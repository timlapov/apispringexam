package art.lapov.apispringexam.service;

import art.lapov.apispringexam.controller.dto.ExpenseCreateDto;
import art.lapov.apispringexam.controller.dto.ExpenseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ExpenseService {
    ExpenseDto createExpense(String eventId, ExpenseCreateDto expenseCreateDto);

    @Transactional(readOnly = true)
    List<ExpenseDto> getExpenses(String eventId, String payerId, Double amountGt, Double amountLt);
}
