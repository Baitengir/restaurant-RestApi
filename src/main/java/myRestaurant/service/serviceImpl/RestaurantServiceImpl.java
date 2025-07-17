package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.restaurantDto.request.RestaurantRequest;
import myRestaurant.dto.restaurantDto.request.RestaurantRequestUpdate;
import myRestaurant.dto.restaurantDto.response.RestaurantResponse;
import myRestaurant.entities.Restaurant;
import myRestaurant.repo.RestaurantRepo;
import myRestaurant.service.RestaurantService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Override
    public SimpleResponse save(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .type(restaurantRequest.getType())
                .service(restaurantRequest.getService())
                .build();

        restaurantRepo.save(restaurant);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Restaurant saved")
                .build();
    }

    @Override
    public RestaurantResponse getById(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Restaurant with id %s not found", id))
        );

        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .type(restaurant.getType())
                .service(restaurant.getService())
                .numberOfEmployees(restaurant.getNumberOfEmployees())
                .build();
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepo.getAllRestaurants();
    }

    @Override
    public SimpleResponse updateById(Long id, RestaurantRequestUpdate restaurantRequestUpdate) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Restaurant with id %s not found", id))
        );

        restaurant.setName(restaurantRequestUpdate.name());
        restaurant.setLocation(restaurantRequestUpdate.location());
        restaurant.setType(restaurantRequestUpdate.type());
        restaurant.setService(restaurantRequestUpdate.service());
        restaurantRepo.save(restaurant);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant updated")
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Restaurant with id %s not found", id))
        );

        restaurant.getUsers().size();
        restaurant.getMenuItems().size();
        restaurantRepo.delete(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant deleted")
                .build();
    }
}
