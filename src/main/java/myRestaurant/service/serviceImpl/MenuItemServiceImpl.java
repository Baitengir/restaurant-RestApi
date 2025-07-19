package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.dto.menuItemDto.request.MenuItemRequest;
import myRestaurant.dto.menuItemDto.response.MenuItemResponse;
import myRestaurant.entities.*;
import myRestaurant.repo.MenuItemsRepo;
import myRestaurant.repo.RestaurantRepo;
import myRestaurant.repo.SubCategoryRepo;
import myRestaurant.repo.UserRepo;
import myRestaurant.service.MenuItemService;
import myRestaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static myRestaurant.enums.Role.WAITER;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemsRepo menuItemsRepo;
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;
    private final SubCategoryRepo subCategoryRepo;

    @Override
    public SimpleResponse saveByRestaurantId(Long userId, Long restaurantId, Long subCategoryId, MenuItemRequest menuItemRequest) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", userId))
        );
        if (user.getRole().equals(WAITER)) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("You are not allowed to save this menu item")
                    .build();
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(
                () -> new NullPointerException(String.format("Restaurant with id %s not found", restaurantId))
        );

        if (menuItemRequest.price() < 0){
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Price must be bigger than zero")
                    .build();
        }

        MenuItem menuItem = MenuItem.builder()
                .name(menuItemRequest.name())
                .description(menuItemRequest.description())
                .price(menuItemRequest.price())
                .description(menuItemRequest.description())
                .image(menuItemRequest.image())
                .isVegetarian(menuItemRequest.isVegetarian())
                .build();

        SubCategory subCategory = subCategoryRepo.findById(subCategoryId).orElseThrow(
                () -> new NullPointerException(String.format("SubCategory with id %s not found", subCategoryId))
        );

        menuItem.setRestaurant(restaurant);
        restaurant.getMenuItems().add(menuItem);
        menuItem.setSubCategory(subCategory);
        subCategory.getMenuItems().add(menuItem);
        menuItemsRepo.save(menuItem);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Menu item saved")
                .build();
    }

    @Override
    public MenuItemResponse getById(Long id) {
        MenuItem menuItem = menuItemsRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Menu item with id %s not found", id))
        );
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .image(menuItem.getImage())
                .isVegetarian(menuItem.isVegetarian())
                .build();
    }

    @Override
    public List<MenuItemResponse> getAll() {
        return menuItemsRepo.getAll();
    }

    @Override
    public SimpleResponse updateById(Long id, MenuItemRequest menuItemRequest) {
        MenuItem menuItem = menuItemsRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Menu item with id %s not found", id))
        );

        menuItem.setName(menuItemRequest.name());
        menuItem.setDescription(menuItemRequest.description());
        menuItem.setPrice(menuItemRequest.price());
        menuItem.setImage(menuItemRequest.image());
        menuItem.setVegetarian(menuItemRequest.isVegetarian());
        menuItemsRepo.save(menuItem);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Menu item updated")
                .build();
    }

    @Override
    @Transactional
    public SimpleResponse deleteById(Long id) {
        MenuItem menuItem = menuItemsRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Menu item with id %s not found", id))
        );
        menuItem.setRestaurant(null);
        menuItem.setStopList(null);
        for (Cheque cheque : menuItem.getCheques()) {
            cheque.getMenuItems().remove(menuItem);
        }

//        menuItem.getCheques().clear();
        menuItemsRepo.delete(menuItem);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Menu item deleted")
                .build();
    }

    @Override
    public List<MenuItemResponse> globalSearch(String keyword) {
        return menuItemsRepo.globalSearch(keyword);
    }

    @Override
    public List<MenuItemResponse> sortByPrice(Long restaurantId, String ascOrDesc) {
        if (ascOrDesc.equals("desc")) {
            return menuItemsRepo.sortByPriceDesc(restaurantId);
        } else if("asc".equals(ascOrDesc)) {
            return menuItemsRepo.sortByPriceAsc(restaurantId);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<MenuItemResponse> getAllVegetarianFood(Long restaurantId) {
        return menuItemsRepo.getAllVegetarianFood(restaurantId);
    }
}
