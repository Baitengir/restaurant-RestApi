package myRestaurant.repo;

import myRestaurant.dto.stopListDto.response.StopListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.StopList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopListRepo extends JpaRepository<StopList, Long> {

    @Query(value = """
            select count(*) > 0
            from menu_items mi
            join stop_lists sl on sl.menu_item_id = mi.id
            where mi.name = :name
            """, nativeQuery = true)
    boolean isExistingFoodInStopList(@Param("name") String name);

    @Query("""
            select new myRestaurant.dto.stopListDto.response.StopListResponse(
                        id, name, date
                        )
            from StopList
            """)
    public List<StopListResponse> getAll();

}
