package myRestaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.stopListDto.request.StopListRequest;
import myRestaurant.dto.stopListDto.response.StopListResponse;
import myRestaurant.entities.MenuItem;
import myRestaurant.entities.StopList;
import myRestaurant.entities.User;
import myRestaurant.repo.MenuItemsRepo;
import myRestaurant.repo.StopListRepo;
import myRestaurant.repo.UserRepo;
import myRestaurant.service.StopListService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

import static myRestaurant.enums.Role.WAITER;

@Service
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {
    private final StopListRepo stopListRepo;
    private final UserRepo userRepo;
//    private final SubCategoryRepo subCategoryRepo;
    private final MenuItemsRepo menuItemsRepo;


    @Override
    public SimpleResponse save(Long userId, Long menuItemId, StopListRequest stopListRequest) {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new NullPointerException(String.format("User with id %s not found", userId))
        );

        if (user.getRole().equals(WAITER)) {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Save stop list can be only 'admin' or 'chef'")
                    .build();
        }

        MenuItem menuItem = menuItemsRepo.findById(menuItemId).orElseThrow(
                () -> new NullPointerException(String.format("menuItem with id %s not found", menuItemId))
        );

        if (stopListRepo.isExistingFoodInStopList(menuItem.getName())){
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(String.format("food by name %s already in stop list", menuItem.getName()))
                    .build();
        }

        StopList stopList = StopList.builder()
                .name(stopListRequest.name())
                .menuItem(menuItem)
                .date(LocalDate.now())
                .build();
        stopListRepo.save(stopList);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Stop list successfully saved")
                .build();
    }

    @Override
    public StopListResponse getById(Long id) {
        StopList stopList = stopListRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("StopList with id %s not found", id))
        );

        return StopListResponse.builder()
                .id(stopList.getId())
                .name(stopList.getName())
                .date(stopList.getDate())
                .build();
    }

    @Override
    public List<StopListResponse> getAll() {
        return stopListRepo.getAll();
    }

    @Override
    public SimpleResponse update(Long id, StopListRequest stopListRequest) {
        StopList stopList = stopListRepo.findById(id).orElseThrow(
                () -> new NullPointerException(String.format("StopList with id %s not found", id))
        );

        stopList.setName(stopListRequest.name());
        stopListRepo.save(stopList);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Stop list successfully updated")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        return null;
    }
}
