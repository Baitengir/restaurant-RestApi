package myRestaurant.api;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.dto.menuItemDto.request.MenuItemRequest;
import myRestaurant.dto.menuItemDto.response.MenuItemResponse;
import myRestaurant.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/menuItems")
@RestController
@RequiredArgsConstructor
public class MenuItemApi {
    private final MenuItemService menuItemService;

    @PostMapping()
    public SimpleResponse saveByRestaurantId(@RequestParam Long userId,
                                             @RequestParam Long restaurantId,
                                             @RequestParam Long subCategoryId,
                                             @RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.saveByRestaurantId(userId, restaurantId, subCategoryId, menuItemRequest);
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public MenuItemResponse getById(@PathVariable Long id) {
        return menuItemService.getById(id);
    }

    // ✅ Get all
    @GetMapping
    public List<MenuItemResponse> getAll() {
        return menuItemService.getAll();
    }

    // ✅ Update by ID
    @PutMapping("/{id}")
    public ResponseEntity<SimpleResponse> updateById(
            @PathVariable Long id,
            @RequestBody MenuItemRequest menuItemRequest
    ) {
        SimpleResponse response = menuItemService.updateById(id, menuItemRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    // 🔍 1. Global Search (поиск по имени)
    @GetMapping("/search")
    public List<MenuItemResponse> globalSearch(@RequestParam String keyword) {
        return menuItemService.globalSearch(keyword);
    }

    // 🔼🔽 2. Сортировка по цене
    @GetMapping("/sort")
    public List<MenuItemResponse> sortByPrice(@RequestParam Long restaurantId,
                                              @RequestParam(defaultValue = "asc") String ascOrDesc) {
        return menuItemService.sortByPrice(restaurantId, ascOrDesc);
    }

    // 🥦 3. Только вегетарианские блюда
    @GetMapping("/vegetarian")
    public List<MenuItemResponse> getAllVegetarianFood(@RequestParam Long restaurantId) {
        return menuItemService.getAllVegetarianFood(restaurantId);
    }
}
