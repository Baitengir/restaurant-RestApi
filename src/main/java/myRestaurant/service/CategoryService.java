package myRestaurant.service;

import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.categoryDto.request.CategoryRequest;
import myRestaurant.dto.categoryDto.response.CategoryResponse;
import myRestaurant.entities.Category;

import java.util.List;

public interface CategoryService {
    SimpleResponse save (CategoryRequest categoryRequest);
    CategoryResponse getById(Long id);
    List<CategoryResponse> getAll ();
    SimpleResponse updateById (Long id, CategoryRequest categoryRequest);
    SimpleResponse delete (Long id);
}
