package myRestaurant.service;

import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.menuItemDto.request.MenuItemRequest;
import myRestaurant.dto.menuItemDto.response.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
    SimpleResponse saveByRestaurantId(Long userId, Long restaurantId, Long subCategoryId, MenuItemRequest menuItemRequest);
    MenuItemResponse getById(Long id);
    List<MenuItemResponse> getAll ();
    SimpleResponse updateById (Long id, MenuItemRequest menuItemRequest);
    SimpleResponse deleteById(Long id);
}
