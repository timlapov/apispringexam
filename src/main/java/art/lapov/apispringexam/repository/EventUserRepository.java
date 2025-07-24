package art.lapov.apispringexam.repository;

import art.lapov.apispringexam.entity.EventUser;
import art.lapov.apispringexam.entity.EventUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventUserRepository extends JpaRepository<EventUser, EventUserId> {
}
