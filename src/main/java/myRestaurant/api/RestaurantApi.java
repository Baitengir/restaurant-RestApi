package myRestaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.restaurantDto.request.RestaurantRequest;
import myRestaurant.dto.restaurantDto.request.RestaurantRequestUpdate;
import myRestaurant.dto.restaurantDto.response.RestaurantResponse;
import myRestaurant.service.RestaurantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/restaurants")
public class RestaurantApi {
    private final RestaurantService restaurantService;

    @PostMapping()
    public SimpleResponse saveRestaurant (@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.save(restaurantRequest);
    }

    @GetMapping("/{id}")
    public RestaurantResponse getRestaurantById(@PathVariable Long id) {
        return restaurantService.getById(id);
    }

    @GetMapping
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PutMapping("/{id}")
    public SimpleResponse updateRestaurantById(@PathVariable Long id,
                                               @RequestBody RestaurantRequestUpdate restaurantRequestUpdate) {
        return restaurantService.updateById(id, restaurantRequestUpdate);
    }


}
