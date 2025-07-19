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

    public MenuItemResponse(Long id, String name, String image, int price, String description, boolean isVegetarian) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }

    public MenuItemResponse() {
    }
}
