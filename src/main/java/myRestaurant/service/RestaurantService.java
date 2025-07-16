package myRestaurant.service;

import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.restaurantDto.request.RestaurantRequest;
import myRestaurant.dto.restaurantDto.request.RestaurantRequestUpdate;
import myRestaurant.dto.restaurantDto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    SimpleResponse save (RestaurantRequest restaurantRequest);
    RestaurantResponse getById(Long id);
    List<RestaurantResponse> getAllRestaurants();
    SimpleResponse updateById (Long id, RestaurantRequestUpdate restaurantRequestUpdate);
    SimpleResponse deleteById(Long id);
    // todo 15 emp
}
