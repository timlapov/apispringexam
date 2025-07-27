package art.lapov.apispringexam.service.impl;

import art.lapov.apispringexam.business.BalanceService;
import art.lapov.apispringexam.controller.dto.BalanceDto;
import art.lapov.apispringexam.controller.dto.DebtDto;
import art.lapov.apispringexam.controller.dto.EventCreateDto;
import art.lapov.apispringexam.controller.dto.EventDto;
import art.lapov.apispringexam.controller.dto.mapper.EventMapper;
import art.lapov.apispringexam.entity.*;
import art.lapov.apispringexam.repository.EventRepository;
import art.lapov.apispringexam.repository.EventUserRepository;
import art.lapov.apispringexam.repository.UserRepository;
import art.lapov.apispringexam.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventUserRepository euRepository;
    private final UserRepository userRepository;
    private final BalanceService balanceService;

    @Override
    public EventDto create(EventCreateDto dto) {
        Event event = new Event();
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setCreatedAt(LocalDateTime.now());
        event = eventRepository.save(event);

        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        euRepository.save(new EventUser(
                new EventUserId(event.getId(), owner.getId()),
                owner, event, Role.OWNER));

        Event savedEvent = eventRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found after creation"));
        return eventMapper.toDto(savedEvent);
    }

    @Override
    public EventDto getById(String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found: " + id));
        return eventMapper.toDto(event);
    }

    @Override
    public List<EventDto> getAll() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .toList();
    }

    @Override
    public BalanceDto getBalance(String eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found: " + eventId));

        List<User> participants = event.getParticipants().stream()
                .map(EventUser::getUser)
                .toList();

        List<Expense> expenses = event.getExpenses();

        List<DebtDto> debts = balanceService.calculate(participants, expenses, event.getPayments());
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();

        return new BalanceDto(debts, totalExpenses);
    }


}
