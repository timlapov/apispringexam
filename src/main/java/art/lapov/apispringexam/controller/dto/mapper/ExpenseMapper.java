package art.lapov.apispringexam.controller.dto.mapper;

import art.lapov.apispringexam.controller.dto.ExpenseDto;
import art.lapov.apispringexam.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {
    @Mapping(source = "user.id", target = "payerId")
    @Mapping(source = "event.id", target = "eventId")
    ExpenseDto toDto(Expense expense);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "event", ignore = true)
    Expense toEntity(ExpenseDto dto);
}
