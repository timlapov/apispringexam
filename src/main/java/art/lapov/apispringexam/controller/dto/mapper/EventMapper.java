package art.lapov.apispringexam.controller.dto.mapper;

import art.lapov.apispringexam.controller.dto.EventDto;
import art.lapov.apispringexam.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    EventDto toDto(Event event);
    Event toEntity(EventDto dto);
}
