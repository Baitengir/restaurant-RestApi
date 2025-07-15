package restaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import restaurant.enums.RestaurantType;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen", sequenceName = "restaurant_seq", allocationSize = 1, initialValue = 1)
    Long id;
    String name;
    String location;
    RestaurantType type;
    int numberOfEmployees;
    int service;
    @OneToMany (mappedBy = "restaurant")
    List<User> users;
}
