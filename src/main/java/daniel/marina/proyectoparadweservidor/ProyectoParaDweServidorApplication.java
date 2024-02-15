package daniel.marina.proyectoparadweservidor;

import daniel.marina.proyectoparadweservidor.dto.user.UserDtoCreate;
import daniel.marina.proyectoparadweservidor.model.Department;
import daniel.marina.proyectoparadweservidor.model.Role;
import daniel.marina.proyectoparadweservidor.model.Worker;
import daniel.marina.proyectoparadweservidor.services.DataInsertionService;
import daniel.marina.proyectoparadweservidor.services.DepartmentService;
import daniel.marina.proyectoparadweservidor.services.UserService;
import daniel.marina.proyectoparadweservidor.services.WorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class ProyectoParaDweServidorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoParaDweServidorApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(DataInsertionService dataInsertionService, UserService userService, DepartmentService departmentService, WorkerService workerService) {
        return args -> {
            dataInsertionService.insertUsers(userService, 10);
            dataInsertionService.insertDepartments(departmentService, 5);
            dataInsertionService.insertWorkers(workerService, departmentService, 20);
        };
    }
}
