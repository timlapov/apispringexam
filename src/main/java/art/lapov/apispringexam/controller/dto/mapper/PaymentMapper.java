package art.lapov.apispringexam.controller.dto.mapper;

import art.lapov.apispringexam.controller.dto.PaymentDto;
import art.lapov.apispringexam.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
//    @Mapping(source = "fromUser.id", target = "fromUserId")
//    @Mapping(source = "toUser.id", target = "toUserId")
//    @Mapping(source = "event.id", target = "eventId")
    PaymentDto toDto(Payment payment);

    Payment toEntity(PaymentDto dto);
}
