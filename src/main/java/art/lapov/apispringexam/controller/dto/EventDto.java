package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link art.lapov.apispringexam.entity.Event}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String id;
    private String description;
    private String name;
    private LocalDateTime createdAt;
    private String ownerId;
    private List<String> participantIds;
}
