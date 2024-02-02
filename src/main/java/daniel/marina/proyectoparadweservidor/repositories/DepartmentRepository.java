package daniel.marina.proyectoparadweservidor.repositories;

import daniel.marina.proyectoparadweservidor.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Optional<Department> findDepartmentByNumber (int number);
    List<Department> findDepartmentsByNameContainsIgnoreCase(String name);
}
