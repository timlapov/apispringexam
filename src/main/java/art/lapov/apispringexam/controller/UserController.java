package art.lapov.apispringexam.controller;

import art.lapov.apispringexam.controller.dto.UserDto;
import art.lapov.apispringexam.controller.dto.mapper.UserMapper;
import art.lapov.apispringexam.entity.User;
import art.lapov.apispringexam.security.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Map<String, String>> getUser(@PathVariable String email) {
        User user = (User) userService.loadUserByUsername(email);
        Map<String, String> response = Map.of(
                "name", user.getName(),
                "email", user.getEmail()
        );
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.convertToUser(userDto);
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDto.getEmail());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody @Valid UserDto userDto, @PathVariable String id) {
        User user = userMapper.convertToUser(userDto);
        user.setId(id);
        User userUpdated = userService.update(user);
        Map<String, String> response = Map.of(
                "id", userUpdated.getId(),
                "name", userUpdated.getName(),
                "email", userUpdated.getEmail()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

}
