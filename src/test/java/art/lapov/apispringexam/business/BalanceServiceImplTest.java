package art.lapov.apispringexam.business;

import art.lapov.apispringexam.controller.dto.DebtDto;
import art.lapov.apispringexam.entity.Expense;
import art.lapov.apispringexam.entity.Payment;
import art.lapov.apispringexam.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceServiceImplTest {

    private final BalanceService balanceService = new BalanceServiceImpl();

    @Test
    void testCalculate_NoExpensesNoPayments() {
        User user1 = new User();
        user1.setId("1");
        user1.setName("User1");
        user1.setEmail("user1@example.com");
        
        User user2 = new User();
        user2.setId("2");
        user2.setName("User2");
        user2.setEmail("user2@example.com");
        List<User> participants = Arrays.asList(user1, user2);

        List<DebtDto> debts = balanceService.calculate(participants, Collections.emptyList(), Collections.emptyList());

        assertEquals(0, debts.size());
    }

    @Test
    void testCalculate_WithExpensesNoPayments() {
        User user1 = new User();
        user1.setId("1");
        user1.setName("User1");
        user1.setEmail("user1@example.com");
        
        User user2 = new User();
        user2.setId("2");
        user2.setName("User2");
        user2.setEmail("user2@example.com");
        List<User> participants = Arrays.asList(user1, user2);

        Expense expense = new Expense("1", 100.1, "Expense 1", LocalDateTime.now(), null, user1);
        List<Expense> expenses = Collections.singletonList(expense);

        List<DebtDto> debts = balanceService.calculate(participants, expenses, Collections.emptyList());

        assertEquals(1, debts.size());
        DebtDto debt = debts.getFirst();
        assertEquals("user2@example.com", debt.getFromUser());
        assertEquals("user1@example.com", debt.getToUser());
        assertEquals(50.05, debt.getAmount(), 0.001);
    }

    @Test
    void testCalculate_WithExpensesAndPayments() {
        User user1 = new User();
        user1.setId("1");
        user1.setName("User1");
        user1.setEmail("user1@example.com");
        
        User user2 = new User();
        user2.setId("2");
        user2.setName("User2");
        user2.setEmail("user2@example.com");
        List<User> participants = Arrays.asList(user1, user2);

        Expense expense = new Expense("1", 100.1, "Expense 1", LocalDateTime.now(), null, user1);
        List<Expense> expenses = Collections.singletonList(expense);

        Payment payment = new Payment("1", 20.0, LocalDateTime.now(), user2, user1, null);
        List<Payment> payments = Collections.singletonList(payment);

        List<DebtDto> debts = balanceService.calculate(participants, expenses, payments);

        assertEquals(1, debts.size());
        DebtDto debt = debts.getFirst();
        assertEquals("user2@example.com", debt.getFromUser());
        assertEquals("user1@example.com", debt.getToUser());
        assertEquals(30.05, debt.getAmount(), 0.001);
    }
}
