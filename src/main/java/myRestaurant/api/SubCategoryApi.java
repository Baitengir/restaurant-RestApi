package myRestaurant.api;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.subCategoryDto.request.SubCategoryRequest;
import myRestaurant.dto.subCategoryDto.response.SubCategoryResponse;
import myRestaurant.service.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/subcategories")
public class SubCategoryApi {
    private final SubCategoryService subCategoryService;

    // POST /api/subcategories/{categoryId}
    @PostMapping("/{categoryId}")
    public SimpleResponse saveSubCategory(@PathVariable Long categoryId,
                                          @RequestBody SubCategoryRequest subCategoryRequest) {
        return subCategoryService.saveByCategoryId(categoryId, subCategoryRequest);
    }

    // GET /api/subcategories/{id}
    @GetMapping("/{id}")
    public SubCategoryResponse getSubCategoryById(@PathVariable Long id) {
        return subCategoryService.getById(id);
    }

    // GET /api/subcategories
    @GetMapping
    public List<SubCategoryResponse> getAllSubCategories() {
        return subCategoryService.getAll();
    }

    // PUT /api/subcategories/{id}
    @PutMapping("/{id}")
    public SimpleResponse updateSubCategoryById(@PathVariable Long id,
                                                @RequestBody SubCategoryRequest subCategoryRequest) {
        return subCategoryService.updateById(id, subCategoryRequest);
    }
}
