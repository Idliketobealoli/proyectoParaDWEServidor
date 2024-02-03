package daniel.marina.proyectoparadweservidor;

import daniel.marina.proyectoparadweservidor.dto.user.UserDtoCreate;
import daniel.marina.proyectoparadweservidor.model.Department;
import daniel.marina.proyectoparadweservidor.model.Role;
import daniel.marina.proyectoparadweservidor.model.Worker;
import daniel.marina.proyectoparadweservidor.services.DepartmentService;
import daniel.marina.proyectoparadweservidor.services.UserService;
import daniel.marina.proyectoparadweservidor.services.WorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class ProyectoParaDweServidorApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProyectoParaDweServidorApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UserService userService, DepartmentService departmentService, WorkerService workerService) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        UserDtoCreate admin = new UserDtoCreate(
                "admin@company.com",
                encoder.encode("admin1234"),
                Role.ADMIN
        );

        Department[] departments = new Department[2];
        departments[0] = new Department(UUID.randomUUID(), "department", "This is a department", 1);
        departments[1] = new Department(UUID.randomUUID(), "department", "This is a department", 2);

        return args -> {
            userService.create(admin);
            for (Department d : departments) {
                departmentService.create(d);
            }
            // Aquí porque sino da error porque se crean antes que los departamentos
            Worker[] workers = new Worker[3];
            workers[0] = new Worker(UUID.randomUUID(), "worker1", "w1@company.com", "667898789", departments[0].getId());
            workers[1] = new Worker(UUID.randomUUID(), "worker3", "w3@company.com", "734298789", departments[1].getId());
            workers[2] = new Worker(UUID.randomUUID(), "worker2", "w2@company.com", "667443789", departments[0].getId());

            for (Worker w : workers) {
                workerService.create(w);
            }
        };
    }
}
