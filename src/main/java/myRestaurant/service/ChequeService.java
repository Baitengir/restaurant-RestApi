package myRestaurant.service;

import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.chequeDto.request.ChequeRequest;
import myRestaurant.dto.chequeDto.request.ChequeRequestUpdate;
import myRestaurant.dto.chequeDto.response.ChequeResponse;

import java.time.LocalDate;
import java.util.List;

public interface ChequeService {
    SimpleResponse save (Long userId, ChequeRequest chequeRequest);
    ChequeResponse getById(Long id);
    List<ChequeResponse> getAll();
    SimpleResponse update (Long id, Long requestOwnerId, ChequeRequestUpdate chequeRequestUpdate);
    SimpleResponse delete (Long id, Long requestOwnerId);
    List<ChequeResponse> getAllChequesByUserId(Long userId);
    double getAvgPriceByRestaurantIdInTheDay (Long requestOwnerId, Long restaurantId, LocalDate localDate);
    double getTotalPriceByUserIdInTheDay (Long userId, LocalDate localDate);
}
