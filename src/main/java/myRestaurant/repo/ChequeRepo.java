package myRestaurant.repo;

import myRestaurant.dto.chequeDto.response.ChequeResponse;
import myRestaurant.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import myRestaurant.entities.Cheque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeRepo extends JpaRepository<Cheque, Long> {
    //        Long id;
//        double priceAverage;
//        LocalDateTime createdAt;
//        int Service;
//        int grandTotal;
//        List<MenuItem> menuItems;

    @Query("""
            select new myRestaurant.dto.chequeDto.response.ChequeResponse (
                        c.id,c.priceAverage,c.createdAt,c.service,c.grandTotal
                        )
            from Cheque c
            """)
    List<ChequeResponse> getAll();

    @Query("""
            select new myRestaurant.dto.chequeDto.response.ChequeResponse (
                        c.id,c.priceAverage,c.createdAt,c.service,c.grandTotal
                        )
            from Cheque c
            where c.user.id = :userId
            """)
    List<ChequeResponse> getAllChequesByUserId(Long userId);

    @Query(value = """
            select ch.* from cheques ch
            join users u on ch.user_id = u.id
            join restaurants r on u.restaurant_id = r.id
            where r.id = :restaurantId
            """, nativeQuery = true)
    List<Cheque> getAllChequesByRestaurantId(Long restaurantId);


}
