package daniel.marina.proyectoparadweservidor;

import daniel.marina.proyectoparadweservidor.dto.user.UserDtoCreate;
import daniel.marina.proyectoparadweservidor.model.Role;
import daniel.marina.proyectoparadweservidor.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyectoParaDweServidorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoParaDweServidorApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UserService service) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        UserDtoCreate admin = new UserDtoCreate(
                "admin@gmail.com",
                encoder.encode("admin1234"),
                Role.ADMIN
        );
        return args -> {
            service.create(admin);
        };
    }
}
