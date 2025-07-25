package art.lapov.apispringexam.repository;

import art.lapov.apispringexam.entity.Event;
import art.lapov.apispringexam.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
    List<Expense> findByEventId(String eventId);
}
