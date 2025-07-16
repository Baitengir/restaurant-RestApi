package myRestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.StopList;
import org.springframework.stereotype.Repository;

@Repository
public interface StopListRepo  extends JpaRepository<StopList, Long> {
}
