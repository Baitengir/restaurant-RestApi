package myRestaurant.dto.chequeDto.request;

import java.util.List;

public record ChequeRequest (
        Long userId,
        List<Long> menuItemIds
){
}
