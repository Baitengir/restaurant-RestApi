package myRestaurant.dto.chequeDto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myRestaurant.entities.MenuItem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data

public class ChequeResponse {
    Long id;
    double priceAverage;
    LocalDateTime createdAt;
    int service;
    int grandTotal;

    public ChequeResponse(Long id, double priceAverage, LocalDateTime createdAt, int service, int grandTotal) {
        this.id = id;
        this.priceAverage = priceAverage;
        this.createdAt = createdAt;
        this.service = service;
        this.grandTotal = grandTotal;
    }

    public ChequeResponse() {
    }
}
