package myRestaurant.dto.userDto.request;

import myRestaurant.enums.Role;

import java.time.LocalDate;

public record UserRequest (
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        String password,
        String phone,
        Role role,
        int experience
){
}
