package daniel.marina.proyectoparadweservidor.services;

import daniel.marina.proyectoparadweservidor.errors.DepartmentException;
import daniel.marina.proyectoparadweservidor.model.Department;
import daniel.marina.proyectoparadweservidor.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public Department findById(UUID id) throws DepartmentException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentException.DepartmentNotFoundException(
                        "El departamento con id " + id + " no existe."
                ));
    }

    List<Department> findByNameLike(String name) {
        return departmentRepository.findDepartmentsByNameContainsIgnoreCase(name);
    }
}
