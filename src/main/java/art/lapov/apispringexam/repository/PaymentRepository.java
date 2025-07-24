package art.lapov.apispringexam.repository;

import art.lapov.apispringexam.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
