package restaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "stop_lists")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stopList_gen")
    @SequenceGenerator(name = "stopList_gen", sequenceName = "stopList_seq", allocationSize = 1, initialValue = 1)
    Long id;
    String name;
}
