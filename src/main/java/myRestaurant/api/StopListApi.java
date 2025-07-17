package myRestaurant.api;

import lombok.RequiredArgsConstructor;
import myRestaurant.dto.SimpleResponse;
import myRestaurant.dto.stopListDto.request.StopListRequest;
import myRestaurant.dto.stopListDto.response.StopListResponse;
import myRestaurant.service.StopListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stopLists")
public class StopListApi {
    private final StopListService stopListService;

    // ✅ Create
    @PostMapping
    public ResponseEntity<SimpleResponse> save(
            @RequestParam Long userId,
            @RequestParam Long menuItemId,
            @RequestBody StopListRequest stopListRequest
    ) {
        SimpleResponse response = stopListService.save(userId, menuItemId, stopListRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    // ✅ Get All
    @GetMapping
    public List<StopListResponse> getAll() {
        return stopListService.getAll();
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public StopListResponse getById(@PathVariable Long id) {
        return stopListService.getById(id);
    }

    // ✅ Update
    @PutMapping("/{id}")
    public ResponseEntity<SimpleResponse> update(
            @PathVariable Long id,
            @RequestBody StopListRequest stopListRequest
    ) {
        SimpleResponse response = stopListService.update(id, stopListRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResponse> delete(@PathVariable Long id) {
        return null;
    }
}
