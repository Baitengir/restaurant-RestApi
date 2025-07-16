package myRestaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "sub_categories")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategory_gen")
    @SequenceGenerator(name = "subCategory_gen", sequenceName = "subCategory_seq", allocationSize = 1, initialValue = 1)
    Long id;
    String name;
    @ManyToOne
    Category category;
    @OneToMany(mappedBy = "subCategory")
    List<MenuItem> menuItems;
}
