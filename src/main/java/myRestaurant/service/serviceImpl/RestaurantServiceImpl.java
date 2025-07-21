package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.entities.MenuItem;
import myRestaurant.entities.User;
import myRestaurant.myExceptions.TestException;
import myRestaurant.repo.*;
import myRestaurant.service.MenuItemService;
import myRestaurant.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.restaurantDto.request.RestaurantRequest;
import myRestaurant.dto.restaurantDto.request.RestaurantRequestUpdate;
import myRestaurant.dto.restaurantDto.response.RestaurantResponse;
import myRestaurant.entities.Restaurant;
import myRestaurant.service.RestaurantService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final UserRepo userRepo;
    private final MenuItemsRepo menuItemsRepo;
    private final MenuItemService menuItemService;
    private final UserService userService;
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

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
    @Transactional
    public SimpleResponse deleteById(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Restaurant with id %s not found", id))
        );

        new ArrayList<>(restaurant.getUsers()).forEach(user -> userService.deleteById(user.getId()));

        try {
            List<MenuItem> menuItems = new ArrayList<>(restaurant.getMenuItems());
            Set<Long> subCategoryIds = new HashSet<>();
            Set<Long> categoryIds = new HashSet<>();

            for (MenuItem menuItem : menuItems) {
                subCategoryIds.add(menuItem.getSubCategory().getId());
                categoryIds.add(menuItem.getSubCategory().getCategory().getId());
                menuItemService.deleteById(menuItem.getId());
            }

            subCategoryIds.forEach(subCategoryRepo::deleteById);
            categoryIds.forEach(categoryRepo::deleteById);

        } catch (Exception e) {
            throw new TestException("Restaurant delete method failed: " + e.getMessage());
        }

        restaurantRepo.delete(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant deleted")
                .build();
    }

}
