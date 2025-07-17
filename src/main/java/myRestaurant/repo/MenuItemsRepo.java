package myRestaurant.repo;

import myRestaurant.dto.menuItemDto.response.MenuItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.MenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemsRepo extends JpaRepository<MenuItem, Long> {

//    Long id;
//    String name;
//    String image;
//    int price;
//    String description;
//    boolean isVegetarian;

    @Query("""
           select new myRestaurant.dto.menuItemDto.response.MenuItemResponse(
                      id,name,image,price,description,isVegetarian
                      )
           from MenuItem
           """)
    public List<MenuItemResponse> getAll();
}
