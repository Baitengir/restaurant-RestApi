package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.entities.Restaurant;
import myRestaurant.enums.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.userDto.request.UserRequest;
import myRestaurant.dto.userDto.response.UserResponse;
import myRestaurant.entities.User;
import myRestaurant.repo.RestaurantRepo;
import myRestaurant.repo.UserRepo;
import myRestaurant.service.UserService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public SimpleResponse save(Long userId, UserRequest userRequest, Long restaurantId) {
        User requestOwner = userRepo.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        if (requestOwner.getRole().equals(Role.ADMIN)) {

            Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found")
            );
            restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees() + 1);

            User user = User.builder()
                    .firstName(userRequest.firstName())
                    .lastName(userRequest.lastName())
                    .email(userRequest.email())
                    .phone(userRequest.phone())
                    .password(userRequest.password())
                    .birthDate(userRequest.birthDate())
                    .role(userRequest.role())
                    .experience(userRequest.experience())
                    .restaurant(restaurant)
                    .build();

            userRepo.save(user);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.CREATED)
                    .message("User successfully saved")
                    .build();
        } else if (requestOwner.getRole().equals(Role.WAITER)) {
            // todo
        }
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("You dont have the required role")
                .build();
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("user with id %s not found", id))
        );

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .role(user.getRole())
                .experience(user.getExperience())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public SimpleResponse updateById(Long id, UserRequest userRequest) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("user with id %s not found", id))
        );

        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPhone(userRequest.phone());
        user.setPassword(userRequest.password());
        user.setRole(userRequest.role());
        user.setExperience(userRequest.experience());

        userRepo.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("user updated")
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        return null;
    }
}
