package art.lapov.apispringexam.service;

import art.lapov.apispringexam.controller.dto.PaymentCreateDto;
import art.lapov.apispringexam.controller.dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    PaymentDto createPayment(PaymentCreateDto paymentCreateDto);
}
