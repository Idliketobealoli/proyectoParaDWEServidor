package daniel.marina.proyectoparadweservidor.config.security;

import daniel.marina.proyectoparadweservidor.config.security.jwt.JwtAuthenticationFilter;
import daniel.marina.proyectoparadweservidor.config.security.jwt.JwtAuthorizationFilter;
import daniel.marina.proyectoparadweservidor.config.security.jwt.JwtTokenUtils;
import daniel.marina.proyectoparadweservidor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final UserService service;
    private final JwtTokenUtils jwtTokensUtils;

    @Autowired
    public SecurityConfig(UserService service, JwtTokenUtils jwtTokensUtils) {
        this.service = service;
        this.jwtTokensUtils = jwtTokensUtils;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(service).and().build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http);
        http.csrf()
                .disable()
                .exceptionHandling()
                .and()

                // For the JWT token.
                .authenticationManager(authenticationManager)

                // We don't use session states, therefore an Stateless policy.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                // We accept Http requests
                .authorizeHttpRequests()

                // This allows us to show the errors, instead of getting a code FORBIDDEN.
                .requestMatchers("/error/**").permitAll()

                // We allow swagger.
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                // Now we open the /categories/** endpoint.
                .requestMatchers("/categories/**").permitAll()

                // And we allow the following endpoints without verification nor authentication.
                .requestMatchers("/categories").permitAll()

                .requestMatchers("/categories/count/{id}").authenticated()

                .requestMatchers(
                        HttpMethod.DELETE, "/categories/{id}"
                ).hasAnyRole("MANAGER_DELETE", "ADMIN_DELETE")
                .requestMatchers(
                        HttpMethod.POST, "/categories"
                ).hasAnyRole("MANAGER_CREATE", "ADMIN_CREATE")
                .requestMatchers(
                        HttpMethod.PUT, "/categories/{id}"
                ).hasAnyRole("MANAGER_UPDATE", "ADMIN_UPDATE")

                // Any other request will need standard authentication.
                .anyRequest().authenticated()

                .and()

                // We add our authentication filter.
                .addFilter(new JwtAuthenticationFilter(jwtTokensUtils, authenticationManager))
                // And lastly, our authorization filter.
                .addFilter(new JwtAuthorizationFilter(jwtTokensUtils, service, authenticationManager));

        return http.build();
    }
}
