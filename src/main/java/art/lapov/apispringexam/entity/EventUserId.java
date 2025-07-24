package art.lapov.apispringexam.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventUserId implements Serializable {
    private String eventId;
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventUserId)) return false;
        EventUserId that = (EventUserId) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, userId);
    }

}
