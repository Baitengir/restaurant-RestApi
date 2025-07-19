package myRestaurant.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import myRestaurant.entities.User;
import myRestaurant.repo.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class JWTService {

    @Value("${security.secret.key}")
    private String secretKey;
    private final UserRepo userRepo;


    public String generateToken(User user) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        return  JWT.create()
                .withClaim("email", user.getUsername())
                .withClaim("role", user.getRole().name())
                .withClaim("id", user.getId())
                .withIssuedAt(zonedDateTime.toInstant())
                .withExpiresAt(zonedDateTime.plusDays(7).toInstant())
                .sign(getAlgorithm());
    }

    public User verifyToken(String token) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String email = decodedJWT.getClaim("email").asString();
        return userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException(
                                "JWT token does not contain email: " + email
                        )
                );
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }
}

