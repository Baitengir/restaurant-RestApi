package myRestaurant.api;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.categoryDto.request.CategoryRequest;
import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories")
public class CategoryApi {
    private final CategoryService categoryService;

    @PostMapping
    public SimpleResponse saveCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAll();
    }

    @PutMapping("/{id}")
    public SimpleResponse updateCategoryById(@PathVariable Long id,
                                             @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateById(id, categoryRequest);
    }
}
