package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.categoryDto.request.CategoryRequest;
import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.dto.subCategoryDto.response.SubCategoryResponse;
import myRestaurant.dto.subCategoryDto.response.SubCategoryResponseWithCatId;
import myRestaurant.entities.Category;
import myRestaurant.entities.SubCategory;
import myRestaurant.repo.CategoryRepo;
import myRestaurant.repo.SubCategoryRepo;
import myRestaurant.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final SubCategoryRepo subCategoryRepo;

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
                .subCategoryResponses(subCategoryRepo.getAllByCategoryId(category.getId()))
                .build();
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<SubCategoryResponseWithCatId> allWithCategoryId = subCategoryRepo.getAllWithCategoryId();
        List<CategoryResponse> categoryResponses = categoryRepo.getAll();

        for (CategoryResponse categoryResponse : categoryResponses) {
            List<SubCategoryResponse> subs = allWithCategoryId.stream()
                    .filter(sub -> sub.getCategoryId().equals(categoryResponse.getId()))
                    .map(sub -> new SubCategoryResponse(sub.getId(), sub.getName()))
                    .collect(Collectors.toList());
            categoryResponse.setSubCategoryResponses(subs);
        }
        return categoryResponses;
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
    @Transactional
    public SimpleResponse delete(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Category with id %s not found", id))
        );
        for (SubCategory subCategory : category.getSubCategories()) {
            subCategoryRepo.deleteById(subCategory.getId());
        }
        categoryRepo.delete(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Category deleted")
                .build();
    }
}
