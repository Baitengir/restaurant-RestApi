package myRestaurant.repo;

import myRestaurant.dto.subCategoryDto.response.SubCategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.SubCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

    @Query("""
            select new myRestaurant.dto.subCategoryDto.response.SubCategoryResponse(
                        id, name
                        )
            from SubCategory
            """)
    public List<SubCategoryResponse> getAll();

}
