package art.lapov.apispringexam.service;

import art.lapov.apispringexam.controller.dto.BalanceDto;
import art.lapov.apispringexam.controller.dto.EventCreateDto;
import art.lapov.apispringexam.controller.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    EventDto create(EventCreateDto dto);
    EventDto getById(String id);
    List<EventDto> getAll();
    BalanceDto getBalance(String eventId);
}
