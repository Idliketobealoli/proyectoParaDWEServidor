package daniel.marina.proyectoparadweservidor.config;

import daniel.marina.proyectoparadweservidor.config.jwt.JwtAuthenticationFilter;
import daniel.marina.proyectoparadweservidor.config.jwt.JwtTokenUtils;
import daniel.marina.proyectoparadweservidor.mappers.UserMapper;
import daniel.marina.proyectoparadweservidor.repositories.UserRepository;
import daniel.marina.proyectoparadweservidor.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final UserRepository repository;

    private final JwtTokenUtils tokenUtils;

    private final UserMapper mapper;

    public SecurityConfiguration(UserRepository repository, JwtTokenUtils tokenUtils, UserMapper mapper) {
        this.repository = repository;
        this.tokenUtils = tokenUtils;
        this.mapper = mapper;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        // users
                        .requestMatchers(mvc.pattern("/company/users/login")).permitAll()
                        .requestMatchers(mvc.pattern("/company/users/register")).permitAll()
                        // departments
                        .requestMatchers(mvc.pattern("/company/departments/update/")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/company/departments/delete/")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/company/departments/patch/")).hasRole("ADMIN")

                        // workers
                        .requestMatchers(mvc.pattern("/company/workers/update/")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/company/workers/delete/")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/company/workers/patch/")).hasRole("ADMIN")

                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity  http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService(repository, tokenUtils, mapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
}