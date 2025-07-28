package art.lapov.apispringexam.service.impl;

import art.lapov.apispringexam.controller.dto.ExpenseCreateDto;
import art.lapov.apispringexam.controller.dto.ExpenseDto;
import art.lapov.apispringexam.controller.dto.mapper.ExpenseMapper;
import art.lapov.apispringexam.entity.Event;
import art.lapov.apispringexam.entity.Expense;
import art.lapov.apispringexam.entity.User;
import art.lapov.apispringexam.repository.EventRepository;
import art.lapov.apispringexam.repository.ExpenseRepository;
import art.lapov.apispringexam.repository.UserRepository;
import art.lapov.apispringexam.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;

    @Override
    @Transactional
    public ExpenseDto createExpense(String eventId, ExpenseCreateDto expenseCreateDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found: " + eventId));
        User user = userRepository.findById(expenseCreateDto.getPayerId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + expenseCreateDto.getPayerId()));

        Expense expense = new Expense();
        expense.setDescription(expenseCreateDto.getDescription());
        expense.setAmount(expenseCreateDto.getAmount());
        expense.setEvent(event);
        expense.setUser(user);
        expense.setCreatedAt(LocalDateTime.now());

        Expense saved = expenseRepository.save(expense);
        return expenseMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExpenseDto> getExpenses(String eventId, String email, Double amountGt, Double amountLt) {
        List<Expense> expenses = expenseRepository.findByEventId(eventId);
        Stream<Expense> stream = expenses.stream();

        if (email != null) {
            stream = stream.filter(e -> e.getUser().getEmail().equals(email));
        }
        if (amountGt != null) {
            stream = stream.filter(e -> e.getAmount().compareTo(amountGt) > 0);
        }
        if (amountLt != null) {
            stream = stream.filter(e -> e.getAmount().compareTo(amountLt) < 0);
        }

        return stream
                .map(expenseMapper::toDto)
                .toList();
    }
}
