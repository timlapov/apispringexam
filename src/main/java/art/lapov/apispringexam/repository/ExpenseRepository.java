package art.lapov.apispringexam.repository;

import art.lapov.apispringexam.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
}
