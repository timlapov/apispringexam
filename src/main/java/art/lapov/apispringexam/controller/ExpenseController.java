package art.lapov.apispringexam.controller;

import art.lapov.apispringexam.controller.dto.ExpenseCreateDto;
import art.lapov.apispringexam.controller.dto.ExpenseDto;
import art.lapov.apispringexam.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/events/{eventId}/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(@PathVariable String eventId, @RequestBody ExpenseCreateDto expenseCreateDto) {
        ExpenseDto expenseDto = expenseService.createExpense(eventId, expenseCreateDto);
        return ResponseEntity.ok(expenseDto);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getExpenses(
            @PathVariable String eventId,
            @RequestParam(required = false) String payerId,
            @RequestParam(required = false) Double amount_gt,
            @RequestParam(required = false) Double amount_lt) {
        List<ExpenseDto> expenses = expenseService.getExpenses(eventId, payerId, amount_gt, amount_lt);
        return ResponseEntity.ok(expenses);
    }
}
