package myRestaurant.dto.subCategoryDto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubCategoryResponseWithCatId {
    Long id;
    String name;
    Long categoryId;

    public SubCategoryResponseWithCatId(Long id, String name, Long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }
}
