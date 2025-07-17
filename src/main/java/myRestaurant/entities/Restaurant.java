package myRestaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import myRestaurant.enums.RestaurantType;
import java.util.ArrayList;
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
    @Enumerated(EnumType.STRING)
    RestaurantType type;
    int numberOfEmployees = 0;
    int service;
    @OneToMany (mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    List<User> users = new ArrayList<>();
    @OneToMany (mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    List<MenuItem> menuItems = new ArrayList<>();
}
