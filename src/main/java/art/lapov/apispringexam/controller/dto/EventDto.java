package art.lapov.apispringexam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventDto {
    private String description;
    private String name;
}
