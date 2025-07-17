package myRestaurant.dto.menuItemDto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuItemResponse {
    Long id;
    String name;
    String image;
    int price;
    String description;
    boolean isVegetarian;
}
