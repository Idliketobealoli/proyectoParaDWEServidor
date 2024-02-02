package daniel.marina.proyectoparadweservidor.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import daniel.marina.proyectoparadweservidor.dto.user.UserDtoLogin;
import daniel.marina.proyectoparadweservidor.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtTokenUtils jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserDtoLogin credentials = new ObjectMapper().readValue(request.getInputStream(), UserDtoLogin.class);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword()
            );
            return authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        String token = jwtTokenUtil.create(user);

        response.addHeader("Authorization", token);
        response.addHeader("Access-Control-Expose-Headers", JwtTokenUtils.TOKEN_HEADER);
    }

    @Override
    public void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        BadCredentialsError error = new BadCredentialsError();
        response.setStatus(error.getStatus());
        response.setContentType("application/json");
        response.getWriter().append(new ObjectMapper().writeValueAsString(error));
    }

    private static class BadCredentialsError {
        private final long timestamp = new Date().getTime();
        @Getter
        private final int status = 401;
        private final String message = "Error: Incorrect user or password.";
    }
}
