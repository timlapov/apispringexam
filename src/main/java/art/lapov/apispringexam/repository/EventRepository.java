package art.lapov.apispringexam.repository;

import art.lapov.apispringexam.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, String> {
    Optional<Event> findByDescription(String description);
}

