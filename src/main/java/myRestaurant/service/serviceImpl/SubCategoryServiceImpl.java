package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.subCategoryDto.request.SubCategoryRequest;
import myRestaurant.dto.subCategoryDto.response.SubCategoryResponse;
import myRestaurant.entities.Category;
import myRestaurant.entities.MenuItem;
import myRestaurant.entities.SubCategory;
import myRestaurant.repo.CategoryRepo;
import myRestaurant.repo.MenuItemsRepo;
import myRestaurant.repo.SubCategoryRepo;
import myRestaurant.service.SubCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;
    private final MenuItemsRepo menuItemsRepo;

    @Override
    public SimpleResponse saveByCategoryId(Long id, SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = SubCategory.builder()
                .name(subCategoryRequest.name())
                .build();

        Category category = categoryRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")
        );
        category.getSubCategories().add(subCategory);
        subCategory.setCategory(category);
        subCategoryRepo.save(subCategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("SubCategory saved successfully")
                .build();
    }

    @Override
    public SubCategoryResponse getById(Long id) {
        SubCategory subCategory = subCategoryRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategory not found")
        );

        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .build();
    }

    @Override
    public List<SubCategoryResponse> getAll() {
        return subCategoryRepo.getAll();
    }

    @Override
    public SimpleResponse updateById(Long id, SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = subCategoryRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategory not found")
        );

        subCategory.setName(subCategoryRequest.name());
        subCategoryRepo.save(subCategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("SubCategory updated successfully")
                .build();
    }

    @Override
    @Transactional
    public SimpleResponse deleteById(Long id) {
        SubCategory subCategory = subCategoryRepo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategory not found")
        );

        for (MenuItem menuItem : subCategory.getMenuItems()) {
            menuItemsRepo.deleteById(menuItem.getId());
        }
        subCategoryRepo.delete(subCategory);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("SubCategory deleted successfully")
                .build();
    }

    @Override
    public List<SubCategoryResponse> getAllByCategoryId(Long id) {
        return subCategoryRepo.getAllByCategoryId(id);
    }
}
