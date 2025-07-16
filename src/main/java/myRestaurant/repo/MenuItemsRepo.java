package myRestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.MenuItem;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemsRepo extends JpaRepository<MenuItem, Long> {
}
