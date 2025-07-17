package myRestaurant.service;

import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.stopListDto.request.StopListRequest;
import myRestaurant.dto.stopListDto.response.StopListResponse;

import java.util.List;

public interface StopListService {
    SimpleResponse save (Long userId, Long menuItemId, StopListRequest stopListRequest);
    StopListResponse getById (Long id);
    List<StopListResponse> getAll();
    SimpleResponse update (Long id, StopListRequest stopListRequest);
    SimpleResponse delete (Long id);
}
