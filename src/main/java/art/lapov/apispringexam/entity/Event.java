package art.lapov.apispringexam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String description;
    String name;
    LocalDateTime createdAt;
    @OneToMany(mappedBy = "event")
    List<EventUser> participants;

    @OneToMany(mappedBy = "event")
    List<Payment> payments;

    @OneToMany(mappedBy = "event")
    List<Expense> expenses;

}
