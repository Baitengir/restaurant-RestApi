package restaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    LocalDate date;

//    Cheque cheque;

}
