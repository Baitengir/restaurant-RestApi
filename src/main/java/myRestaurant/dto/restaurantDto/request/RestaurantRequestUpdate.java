package myRestaurant.dto.restaurantDto.request;

import myRestaurant.enums.RestaurantType;

public record RestaurantRequestUpdate (
        String name,
        String location,
        RestaurantType type,
        int service
){
}
