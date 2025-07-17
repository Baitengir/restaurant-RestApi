package myRestaurant.service;

import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.userDto.request.UserRequest;
import myRestaurant.dto.userDto.response.UserResponse;

import java.util.List;

public interface UserService {
    SimpleResponse save (Long userId, UserRequest userRequest, Long restaurantId);
    UserResponse getById(Long id);
    List<UserResponse> getAllUsers();
    SimpleResponse updateById(Long id, UserRequest userRequest);
    SimpleResponse deleteById(Long id);
}
