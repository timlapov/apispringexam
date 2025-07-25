package art.lapov.apispringexam.controller;

import art.lapov.apispringexam.controller.dto.PaymentCreateDto;
import art.lapov.apispringexam.controller.dto.PaymentDto;
import art.lapov.apispringexam.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentCreateDto paymentCreateDto) {
        PaymentDto paymentDto = paymentService.createPayment(paymentCreateDto);
        return ResponseEntity.ok(paymentDto);
    }
}
