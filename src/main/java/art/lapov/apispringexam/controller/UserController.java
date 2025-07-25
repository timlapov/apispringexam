package art.lapov.apispringexam.controller;

import art.lapov.apispringexam.controller.dto.UserDto;
import art.lapov.apispringexam.controller.dto.mapper.UserMapper;
import art.lapov.apispringexam.entity.User;
import art.lapov.apispringexam.security.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.convertToUser(userDto);
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDto.getEmail());
    }


}
