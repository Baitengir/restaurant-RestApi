package myRestaurant.repo;

import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.dto.menuItemDto.response.MenuItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.MenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("""
                SELECT new myRestaurant.dto.menuItemDto.response.MenuItemResponse(
                m.id, m.name, m.image, m.price, m.description, m.isVegetarian)
                FROM MenuItem m
                WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    public List<MenuItemResponse> globalSearch(@Param("keyword") String keyword);

    @Query("""
            select new myRestaurant.dto.menuItemDto.response.MenuItemResponse(
            m.id, m.name, m.image, m.price, m.description, m.isVegetarian)
            from MenuItem m
            where m.restaurant.id = :restaurantId
            order by m.price desc
            """)
    public List<MenuItemResponse> sortByPriceDesc(@Param("restaurantId") Long restaurantId);

    @Query("""
            select new myRestaurant.dto.menuItemDto.response.MenuItemResponse(
            m.id, m.name, m.image, m.price, m.description, m.isVegetarian)
            from MenuItem m
            where m.restaurant.id = :restaurantId
            order by m.price
            """)
    public List<MenuItemResponse> sortByPriceAsc(@Param("restaurantId") Long restaurantId);

    @Query("""
            select new myRestaurant.dto.menuItemDto.response.MenuItemResponse(
            m.id, m.name, m.image, m.price, m.description, m.isVegetarian)
            from MenuItem m
            where m.restaurant.id = :restaurantId
            and m.isVegetarian = true
            """)
    public List<MenuItemResponse> getAllVegetarianFood(Long restaurantId);

}
