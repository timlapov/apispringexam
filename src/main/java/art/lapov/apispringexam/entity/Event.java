package art.lapov.apispringexam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;
    private String name;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<EventUser> participants = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<Expense> expenses = new ArrayList<>();

}
