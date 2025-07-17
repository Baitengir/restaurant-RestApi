package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.categoryDto.request.CategoryRequest;
import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.entities.Category;
import myRestaurant.repo.CategoryRepo;
import myRestaurant.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public SimpleResponse save(CategoryRequest categoryRequest) {
        Category build = Category.builder()
                .name(categoryRequest.name())
                .build();

        categoryRepo.save(build);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Category saved")
                .build();
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Category with id %s not found", id))
        );

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepo.getAll();
    }

    @Override
    public SimpleResponse updateById(Long id, CategoryRequest categoryRequest) {

        Category category = categoryRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Category with id %s not found", id))
        );

        category.setName(categoryRequest.name());
        categoryRepo.save(category);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Category updated")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        return null;
    }
}
