package myRestaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_gen")
    @SequenceGenerator(name = "cheque_gen", sequenceName = "cheque_seq", allocationSize = 1, initialValue = 1)
    Long id;
    double priceAverage;
    LocalDateTime createdAt;
    int service;
    @ManyToOne (cascade = CascadeType.DETACH)
    User user;
    int grandTotal;
    @ManyToMany (cascade = CascadeType.DETACH)
    List<MenuItem> menuItems = new ArrayList<>();

    public Cheque(Long id, double priceAverage, LocalDateTime createdAt, int service, int grandTotal, List<MenuItem> menuItems) {
        this.id = id;
        this.priceAverage = priceAverage;
        this.createdAt = createdAt;
        this.service = service;
        this.grandTotal = grandTotal;
        this.menuItems = menuItems;
    }
}
//Waiter’s fullName
//List<MenuItem>items
//Avarage price
//Service %
//Grand total