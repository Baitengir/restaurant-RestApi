package myRestaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "menu_items")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_gen")
    @SequenceGenerator(name = "menu_item_gen", sequenceName = "menu_item_seq", allocationSize = 1, initialValue = 1)
    Long id;
    String name;
    String image;
    int price;
    String description;
    boolean isVegetarian;
    @ManyToMany (mappedBy = "menuItems")
    List<Cheque> cheques;
    @ManyToOne
    SubCategory subCategory;
    @OneToOne
    StopList stopList;
    @ManyToOne
    Restaurant restaurant;

}
