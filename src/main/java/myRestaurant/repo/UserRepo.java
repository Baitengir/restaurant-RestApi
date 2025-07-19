package myRestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import myRestaurant.dto.userDto.response.UserResponse;
import myRestaurant.entities.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

//    Long id;
//    String firstName;
//    String lastName;
//    LocalDate birthDate;
//    String email;
//    String phone;
//    Role role;
//    int experience;

    @Query ("""
            select new myRestaurant.dto.userDto.response.UserResponse(
                        id, firstName, lastName, birthDate, email, phone, role, experience
                        )
            from User
            """)
    public List<UserResponse> getAllUsers();

    Optional<User> findByEmail(String username);


}
