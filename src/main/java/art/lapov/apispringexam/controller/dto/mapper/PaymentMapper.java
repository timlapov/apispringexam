package art.lapov.apispringexam.controller.dto.mapper;

import art.lapov.apispringexam.controller.dto.PaymentDto;
import art.lapov.apispringexam.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    PaymentDto toDto(Payment payment);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventId", target = "event.id")
    Payment toEntity(PaymentDto dto);
}
