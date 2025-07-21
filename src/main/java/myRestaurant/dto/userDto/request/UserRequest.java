package myRestaurant.dto.userDto.request;

import jakarta.validation.constraints.*;
import myRestaurant.enums.Role;

import java.time.LocalDate;

public record UserRequest (
        String name,
        String firstName,
        String lastName,
        @Past(message = "Дата рождения должна быть в прошлом")
        LocalDate birthDate,
        @Email(message = "Invalid email format")
        String email,
        @Size(min = 4, message = "Password must be have minimum 4 symbols")
        String password,
        @Pattern(regexp = "\\+996\\d{9}", message = "Phone number must be the format: +996XXXXXXXXX")
        String phone,
        Role role,
        @Min(value = 0, message = "Experience dont be the negative")
        int experience
){
}
