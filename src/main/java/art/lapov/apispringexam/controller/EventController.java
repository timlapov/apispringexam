package art.lapov.apispringexam.controller;

import art.lapov.apispringexam.controller.dto.BalanceDto;
import art.lapov.apispringexam.controller.dto.EventCreateDto;
import art.lapov.apispringexam.controller.dto.EventDto;
import art.lapov.apispringexam.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    private EventService eventService;

    @PostMapping("/add")
    public ResponseEntity<EventDto> addEvent(@RequestBody EventCreateDto eventCreateDto) {
        EventDto eventDto = eventService.create(eventCreateDto);
        return ResponseEntity.ok(eventDto);
    }

    @GetMapping("/{eventId}/balance")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable String eventId) {
        BalanceDto balanceDto = eventService.getBalance(eventId);
        return ResponseEntity.ok(balanceDto);
    }

}
