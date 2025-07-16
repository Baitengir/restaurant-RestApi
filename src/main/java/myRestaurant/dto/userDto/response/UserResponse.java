package myRestaurant.dto.userDto.response;

import lombok.Builder;
import lombok.Data;
import myRestaurant.enums.Role;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String phone;
    Role role;
    int experience;
}
