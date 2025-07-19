package myRestaurant.dto.categoryDto.response;

import lombok.Builder;
import lombok.Data;
import myRestaurant.dto.subCategoryDto.response.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CategoryResponse {
    Long id;
    String name;
    List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();

    public CategoryResponse(Long id, String name, List<SubCategoryResponse> subCategoryResponses) {
        this.id = id;
        this.name = name;
        this.subCategoryResponses = subCategoryResponses;
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
