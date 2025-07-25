package art.lapov.apispringexam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String description;
    String name;
    LocalDateTime createdAt;
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    List<EventUser> participants = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    List<Expense> expenses = new ArrayList<>();

}
