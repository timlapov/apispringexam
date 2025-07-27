package art.lapov.apispringexam.controller;

import art.lapov.apispringexam.controller.dto.UserDto;
import art.lapov.apispringexam.controller.dto.mapper.UserMapper;
import art.lapov.apispringexam.entity.User;
import art.lapov.apispringexam.security.SecurityConfig;
import art.lapov.apispringexam.security.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddUser() throws Exception {
        UserDto userDto = new UserDto("Test User", "test@example.com", "password");
        User user = new User();
        user.setEmail("test@example.com");

        when(userMapper.convertToUser(any(UserDto.class))).thenReturn(user);
        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(status().isCreated())
            .andExpect(content().string("test@example.com"));
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void testGetUser() throws Exception {
        User user = new User("1", "Test User", "test@example.com", "password", null, null, null);

        when(userService.loadUserByUsername("test@example.com")).thenReturn(user);

        mockMvc.perform(get("/api/users/test@example.com"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Test User"))
            .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void testGetUser_NotFound() throws Exception {
        when(userService.loadUserByUsername(anyString())).thenThrow(new UsernameNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/nonexistent@example.com"))
            .andExpect(status().isNotFound());
    }


    @Test
    @WithMockUser(username = "test@example.com")
    void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto("Updated User", "test@example.com", "newpassword");
        User user = new User("1", "Updated User", "test@example.com", "newpassword", null, null, null);

        when(userMapper.convertToUser(any(UserDto.class))).thenReturn(user);
        when(userService.update(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Updated User"))
            .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void testDeleteUser() throws Exception {
        doNothing().when(userService).delete("1");

        mockMvc.perform(delete("/api/users/delete/1"))
            .andExpect(status().isNoContent());
    }
}
