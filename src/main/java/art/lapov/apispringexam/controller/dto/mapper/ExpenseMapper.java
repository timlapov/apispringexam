package art.lapov.apispringexam.controller.dto.mapper;

import art.lapov.apispringexam.controller.dto.ExpenseDto;
import art.lapov.apispringexam.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    ExpenseDto toDto(Expense expense);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventId", target = "event.id")
    Expense toEntity(ExpenseDto dto);
}
