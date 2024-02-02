package daniel.marina.proyectoparadweservidor.config.security.jwt;

import daniel.marina.proyectoparadweservidor.model.User;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtils {
    protected static final String algorithmSecret = "TengoLosCadaveresDeCincoInfantesEnMiRefrigerador.NoSeLoDigasALaPolicia";
    protected static final String TOKEN_TYPE = "JWT";
    protected static final String TOKEN_PREFIX = "Bearer ";
    protected static final String TOKEN_HEADER = "Authorization";

    public String create(User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("typ", TOKEN_TYPE);
        return JWT.create()
                .withSubject(user.getId().toString())
                .withHeader(map)
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1_000) * 2)) // 2 days
                .sign(Algorithm.HMAC512(algorithmSecret));
    }

    public DecodedJWT decode(String token) {
        Algorithm algorithm = Algorithm.HMAC512(algorithmSecret);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }
}
