package daniel.marina.proyectoparadweservidor.config.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import daniel.marina.proyectoparadweservidor.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final JwtTokenUtils jwtTokensUtils;
    private final UserService service;

    public JwtAuthorizationFilter(JwtTokenUtils jwtTokensUtils, UserService service, AuthenticationManager authManager) {
        super(authManager);
        this.jwtTokensUtils = jwtTokensUtils;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(AUTHORIZATION);

        if (header == null || !header.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        getAuthentication(header.substring(7)).ifPresent(authentication -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });

        chain.doFilter(req, res);
    }

    private Optional<UsernamePasswordAuthenticationToken> getAuthentication(String token) {
        DecodedJWT tokenDecoded = jwtTokensUtils.decode(token);

        if (tokenDecoded == null) {
            return Optional.empty();
        }

        String username = tokenDecoded.getClaim("username").asString();

        UserDetails userDetails = service.loadUserByUsername(username);

        return Optional.of(new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        ));
    }
}