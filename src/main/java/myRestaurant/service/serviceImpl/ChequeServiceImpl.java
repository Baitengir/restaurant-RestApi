package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.chequeDto.request.ChequeRequest;
import myRestaurant.dto.chequeDto.request.ChequeRequestUpdate;
import myRestaurant.dto.chequeDto.response.ChequeResponse;
import myRestaurant.entities.Cheque;
import myRestaurant.entities.MenuItem;
import myRestaurant.entities.User;
import myRestaurant.enums.Role;
import myRestaurant.repo.ChequeRepo;
import myRestaurant.repo.MenuItemsRepo;
import myRestaurant.repo.UserRepo;
import myRestaurant.service.ChequeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepo chequeRepo;
    private final UserRepo userRepo;
    private final MenuItemsRepo menuItemsRepo;

    @Override
    public SimpleResponse save(Long requestOwnerId, ChequeRequest chequeRequest) {
        User requestOwner = userRepo.findById(requestOwnerId).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", requestOwnerId))
        );

        if (requestOwner.getRole() == Role.CHEF) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("FORBIDDEN")
                    .build();
        }

        User user = userRepo.findById(chequeRequest.userId()).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", chequeRequest.userId()))
        );

        int totalPrice = 0;
        int restaurantService = 0;
        int loopCount = 0;

        Cheque cheque = new Cheque();

        for (Long menuItemId : chequeRequest.menuItemIds()) {
            MenuItem menuItem = menuItemsRepo.findById(menuItemId).orElseThrow(
                    () -> new NullPointerException(String.format("Menu item with id %s not found", menuItemId))
            );
            loopCount++;

            totalPrice = totalPrice + menuItem.getPrice();
            restaurantService = menuItem.getRestaurant().getService();
            cheque.getMenuItems().add(menuItem);
        }

        cheque.setUser(user);
        cheque.setService(restaurantService);
        cheque.setGrandTotal(totalPrice);
        cheque.setCreatedAt(LocalDateTime.now());
        cheque.setPriceAverage((double) totalPrice /loopCount);

        chequeRepo.save(cheque);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Cheque saved successfully")
                .build();
    }

    @Override
    public ChequeResponse getById(Long id) {
//        Long id;
//        double priceAverage;
//        LocalDateTime createdAt;
//        int Service;
//        int grandTotal;
//        List<MenuItem> menuItems;

        Cheque cheque = chequeRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Cheque with id %s not found", id))
        );
        return ChequeResponse.builder()
                .id(cheque.getId())
                .priceAverage(cheque.getPriceAverage())
                .createdAt(cheque.getCreatedAt())
                .service(cheque.getService())
                .grandTotal(cheque.getGrandTotal())
                .build();
    }

    @Override
    public List<ChequeResponse> getAll() {
        return chequeRepo.getAll();
    }

    @Override
    public SimpleResponse update(Long id, Long requestOwnerId, ChequeRequestUpdate chequeRequestUpdate) {
        User user = userRepo.findById(requestOwnerId).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", requestOwnerId))
        );
        if (user.getRole() == Role.WAITER || user.getRole() == Role.CHEF) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message("You dont change the cheque")
                    .build();
        }

        User userForUpdate = userRepo.findById(chequeRequestUpdate.userId()).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", chequeRequestUpdate.userId()))
        );

        Cheque cheque = chequeRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Cheque with id %s not found", id))
        );
        cheque.setUser(userForUpdate);
        chequeRepo.save(cheque);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cheque updated successfully")
                .build();
    }

    @Override
    @Transactional
    public SimpleResponse delete(Long id, Long requestOwnerId) {
        User requestOwner = userRepo.findById(requestOwnerId).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", requestOwnerId))
        );
        if (requestOwner.getRole() == Role.WAITER || requestOwner.getRole() == Role.CHEF) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message("You dont change the cheque")
                    .build();
        }

        Cheque cheque = chequeRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("Cheque with id %s not found", id))
        );
        cheque.setUser(null);
        cheque.setMenuItems(null);
        chequeRepo.delete(cheque);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cheque deleted successfully")
                .build();
    }

    @Override
    public List<ChequeResponse> getAllChequesByUserId(Long userId) {
        return chequeRepo.getAllChequesByUserId(userId);
    }

    @Override
    public double getAvgPriceByRestaurantIdInTheDay(Long requestOwnerId, Long restaurantId, LocalDate localDate) {
        List<Cheque> cheques = chequeRepo.getAllChequesByRestaurantId(restaurantId);
        int sum = 0;
        int count = 0;
        for (Cheque cheque : cheques) {
            if (cheque.getCreatedAt().toLocalDate().equals(localDate)) {
                count++;
                sum = sum + cheque.getGrandTotal();
            }
        }
        return (double) sum / count;
    }

    @Override
    public double getTotalPriceByUserIdInTheDay(Long userId, LocalDate localDate) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", userId))
        );
        int c = 0, res = 0;

        for (Cheque cheque : user.getCheques()) {
            if (cheque.getCreatedAt().toLocalDate().equals(localDate)) {
                res = res + cheque.getGrandTotal();
                c++;
            }
        }
        return (double) res / c;
    }

}
