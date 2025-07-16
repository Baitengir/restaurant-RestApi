package myRestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import myRestaurant.dto.restaurantDto.response.RestaurantResponse;
import myRestaurant.entities.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

//    Long id;
//    String name;
//    String location;
//    RestaurantType type;
//    int numberOfEmployees = 0;
//    int service;

    @Query("""
            select new myRestaurant.dto.restaurantDto.response.RestaurantResponse (
                        id, name, location, type, numberOfEmployees, service
                        )
            from Restaurant
            """)
    public List<RestaurantResponse> getAllRestaurants();
}
