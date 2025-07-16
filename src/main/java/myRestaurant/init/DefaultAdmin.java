package myRestaurant.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import myRestaurant.entities.User;
import myRestaurant.repo.UserRepo;

import java.time.LocalDate;

import static myRestaurant.enums.Role.ADMIN;

@Component
@RequiredArgsConstructor
public class DefaultAdmin {
    private final UserRepo userRepo;

    @PostConstruct
    public void defaultAdmin() {

        if (userRepo.getAllUsers().isEmpty()) {
            User admin = User.builder()
                    .firstName("Junus")
                    .lastName("Amanov")
                    .email("diamond@gmail.com")
                    .password("jansur123!")
                    .phone("+996507091245")
                    .role(ADMIN)
                    .birthDate(LocalDate.of(2005,11,24))
                    .build();

            userRepo.save(admin);
        }
    }

}
