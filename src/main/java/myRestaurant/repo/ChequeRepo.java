package myRestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.Cheque;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeRepo extends JpaRepository<Cheque, Long> {
}
