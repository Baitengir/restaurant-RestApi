package myRestaurant.service;

import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.subCategoryDto.request.SubCategoryRequest;
import myRestaurant.dto.subCategoryDto.response.SubCategoryResponse;
import java.util.List;

public interface SubCategoryService {
    SimpleResponse saveByCategoryId(Long id, SubCategoryRequest subCategoryRequest);
    SubCategoryResponse getById(Long id);
    List<SubCategoryResponse> getAll ();
    SimpleResponse updateById(Long id, SubCategoryRequest subCategoryRequest);
    SimpleResponse deleteById(Long id);
    List<SubCategoryResponse> getAllByCategoryId(Long id); // todo sort by name
}