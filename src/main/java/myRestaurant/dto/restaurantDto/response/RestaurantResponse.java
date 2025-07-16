package myRestaurant.dto.restaurantDto.response;

import lombok.Builder;
import lombok.Data;
import myRestaurant.enums.RestaurantType;

@Builder
@Data
public class RestaurantResponse {
    Long id;
    String name;
    String location;
    RestaurantType type;
    int numberOfEmployees = 0;
    int service;
}
