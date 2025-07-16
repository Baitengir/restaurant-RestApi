package myRestaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import myRestaurant.enums.Role;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1, initialValue = 1)
    Long id;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String password;
    String phone;
    Role role;
    int experience;
    @ManyToOne
    Restaurant restaurant;
    @OneToMany (mappedBy = "user")
    List<Cheque> cheques;
}
