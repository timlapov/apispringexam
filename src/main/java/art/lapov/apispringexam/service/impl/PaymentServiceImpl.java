package art.lapov.apispringexam.service.impl;

import art.lapov.apispringexam.controller.dto.PaymentCreateDto;
import art.lapov.apispringexam.controller.dto.PaymentDto;
import art.lapov.apispringexam.controller.dto.mapper.PaymentMapper;
import art.lapov.apispringexam.entity.Event;
import art.lapov.apispringexam.entity.Payment;
import art.lapov.apispringexam.entity.User;
import art.lapov.apispringexam.repository.EventRepository;
import art.lapov.apispringexam.repository.PaymentRepository;
import art.lapov.apispringexam.repository.UserRepository;
import art.lapov.apispringexam.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PaymentMapper paymentMapper;

    @Override
    @Transactional
    public PaymentDto createPayment(PaymentCreateDto paymentCreateDto) {
        User fromUser = userRepository.findById(paymentCreateDto.getFromUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + paymentCreateDto.getFromUserId()));

        User toUser = userRepository.findById(paymentCreateDto.getToUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + paymentCreateDto.getToUserId()));

        Event event = eventRepository.findById(paymentCreateDto.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found: " + paymentCreateDto.getEventId()));

        Payment payment = new Payment();
        payment.setFromUser(fromUser);
        payment.setToUser(toUser);
        payment.setAmount(paymentCreateDto.getAmount());
        payment.setEvent(event);
        payment.setCreatedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toDto(savedPayment);
    }
}
