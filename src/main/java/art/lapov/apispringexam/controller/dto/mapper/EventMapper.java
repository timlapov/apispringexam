package art.lapov.apispringexam.controller.dto.mapper;

import art.lapov.apispringexam.controller.dto.EventDto;
import art.lapov.apispringexam.entity.Event;
import art.lapov.apispringexam.entity.EventUser;
import art.lapov.apispringexam.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    default EventDto toDto(Event event) {
        if (event == null) {
            return null;
        }
        
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setCreatedAt(event.getCreatedAt());
        
        dto.setOwnerId(extractOwner(event.getParticipants()));
        dto.setParticipantIds(extractParticipants(event.getParticipants()));
        
        return dto;
    }

    default String extractOwner(List<EventUser> eus) {
        if (eus == null) {
            return null;
        }
        return eus.stream()
                .filter(eu -> eu.getRole() == Role.OWNER)
                .map(eu -> eu.getUser().getId())
                .findFirst()
                .orElse(null);
    }

    default List<String> extractParticipants(List<EventUser> eus) {
        if (eus == null) {
            return List.of();
        }
        return eus.stream()
                .filter(eu -> eu.getRole() == Role.PARTICIPANT)
                .map(eu -> eu.getUser().getId())
                .toList();
    }
}