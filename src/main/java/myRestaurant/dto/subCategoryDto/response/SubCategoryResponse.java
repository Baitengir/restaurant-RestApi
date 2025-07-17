package myRestaurant.dto.subCategoryDto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubCategoryResponse {
    Long id;
    String name;
}
