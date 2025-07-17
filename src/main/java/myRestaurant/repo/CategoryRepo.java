package myRestaurant.repo;

import myRestaurant.dto.categoryDto.response.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query ("""
            select new myRestaurant.dto.categoryDto.response.CategoryResponse(
                        id, name
                        )
            from Category
            """)
    public List<CategoryResponse> getAll();
}
