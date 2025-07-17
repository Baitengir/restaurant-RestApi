package myRestaurant.dto.menuItemDto.request;

public record MenuItemRequest (
        String name,
        String image,
        int price,
        String description,
        boolean isVegetarian
){
}
