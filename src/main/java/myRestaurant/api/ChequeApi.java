package myRestaurant.api;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.chequeDto.request.ChequeRequest;
import myRestaurant.dto.chequeDto.request.ChequeRequestUpdate;
import myRestaurant.dto.chequeDto.response.ChequeResponse;
import myRestaurant.service.ChequeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cheques")
public class ChequeApi {
    private final ChequeService chequeService;

    @PostMapping("/{requestOwnerId}")
    public SimpleResponse save(@PathVariable Long requestOwnerId,
                               @RequestBody ChequeRequest chequeRequest) {
        return chequeService.save(requestOwnerId, chequeRequest);
    }

    @GetMapping("/{id}")
    public ChequeResponse getChequeById(@PathVariable Long id) {
        return chequeService.getById(id);
    }

    @GetMapping
    public List<ChequeResponse> getAllCheques() {
        return chequeService.getAll();
    }

    @PutMapping("/{id}")
    public SimpleResponse updateCheque(@PathVariable Long id,
                                       @RequestParam Long requestOwnerId,
                                       @RequestBody ChequeRequestUpdate chequeRequestUpdate) {
        return chequeService.update(id, requestOwnerId, chequeRequestUpdate);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteCheque(@PathVariable Long id,
                                       @RequestParam Long requestOwnerId) {
        return chequeService.delete(id, requestOwnerId);
    }

    @GetMapping("/user/{userId}")
    public List<ChequeResponse> getChequesByUserId(@PathVariable Long userId) {
        return chequeService.getAllChequesByUserId(userId);
    }

    @GetMapping("/average")
    public double getAveragePriceByRestaurantId(@RequestParam Long requestOwnerId,
                                                @RequestParam Long restaurantId,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return chequeService.getAvgPriceByRestaurantIdInTheDay(requestOwnerId, restaurantId, date);
    }

    @GetMapping("/total")
    public double getTotalPriceByUserAndDate(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return chequeService.getTotalPriceByUserIdInTheDay(userId, date);
    }
}
