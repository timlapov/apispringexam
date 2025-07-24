package art.lapov.apispringexam.controller.dto.mapper;

import art.lapov.apispringexam.controller.dto.UserDto;
import art.lapov.apispringexam.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User convertToUser(UserDto userDto);
}
