package myRestaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name = "category_gen", sequenceName = "category_seq", allocationSize = 1, initialValue = 1)
    Long id;
    @OneToMany (mappedBy = "category")
    List<SubCategory> subCategories;
}
