package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.categoryDto.request.CategoryRequest;
import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.repo.CategoryRepo;
import myRestaurant.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public SimpleResponse save(CategoryRequest categoryRequest) {


        categoryRepo.save()
        return null;
    }

    @Override
    public CategoryResponse getById(Long id) {
        return null;
    }

    @Override
    public List<CategoryResponse> getAll() {
        return List.of();
    }

    @Override
    public SimpleResponse updateById(Long id, CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public SimpleResponse delete(Long id) {
        return null;
    }
}
