package myRestaurant.dto.restaurantDto.request;

import lombok.Builder;
import lombok.Data;
import myRestaurant.enums.RestaurantType;

@Builder
@Data
public class RestaurantRequest {
    String name;
    String location;
    RestaurantType type;
    int service;
}
