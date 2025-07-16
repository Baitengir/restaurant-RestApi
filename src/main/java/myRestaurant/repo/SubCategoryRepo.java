package myRestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.SubCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
}
