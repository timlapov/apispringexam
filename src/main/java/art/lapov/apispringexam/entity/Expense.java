package art.lapov.apispringexam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public Double amount;
    public String description;
    public java.time.LocalDateTime createdAt;
    @ManyToOne
    public Event event;
    @ManyToOne
    public User user;

}
