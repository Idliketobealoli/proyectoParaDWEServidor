package daniel.marina.proyectoparadweservidor.services;

import daniel.marina.proyectoparadweservidor.dto.user.UserDtoCreate;
import daniel.marina.proyectoparadweservidor.model.Department;
import daniel.marina.proyectoparadweservidor.model.Role;
import daniel.marina.proyectoparadweservidor.model.Worker;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Service
public class DataInsertionService {
    Faker faker = new Faker(new Locale("en-US"));

    public void insertUsers(UserService service, int amount) {
        if (amount <= 0) return;
        // One admin
        UserDtoCreate admin = new UserDtoCreate(
                "admin@gmail.com",
                "admin1234",
                Role.ADMIN
                );
        service.create(admin);

        // n-1 users
        for (int i = 0; i < amount-1; i++) {
            UserDtoCreate user = new UserDtoCreate(
                    faker.internet().safeEmailAddress(),
                    faker.internet().password(),
                    Role.USER
                    );
            service.create(user);
        }
    }

    public void insertDepartments(DepartmentService service, int amount) {
        if (amount <= 0) return;

        for (int i = 0; i < amount; i++) {
            Department department = new Department(
                    UUID.randomUUID(),
                    faker.commerce().department(),
                    faker.lorem().paragraph(),
                    i+1
            );
            service.create(department);
        }
    }

    public void insertWorkers(WorkerService service, DepartmentService depService, int amount) {
        if (amount <= 0) return;
        List<Department> departments = depService.findAll();

        for (int i = 0; i < amount; i++) {
            int randomIndex = new Random().nextInt(departments.size());
            UUID randomDepUuid = departments.get(randomIndex).getId();

            Worker worker = new Worker(
                    UUID.randomUUID(),
                    faker.dragonBall().character(),
                    faker.internet().emailAddress(), // Marina mira esto
                    faker.phoneNumber().phoneNumberInternational(),
                    randomDepUuid
            );
            service.create(worker);
        }
    }
}
