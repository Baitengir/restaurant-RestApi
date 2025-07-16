package myRestaurant.api;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.userDto.request.UserRequest;
import myRestaurant.dto.userDto.response.UserResponse;
import myRestaurant.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserApi {

    public final UserService userService;

    @PostMapping
    public SimpleResponse saveUser(@RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public SimpleResponse updateUserById(@PathVariable Long id,
                                         @RequestBody UserRequest userRequest) {
        return userService.updateById(id, userRequest);
    }
}
