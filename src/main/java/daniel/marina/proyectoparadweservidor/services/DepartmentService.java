package daniel.marina.proyectoparadweservidor.services;

import daniel.marina.proyectoparadweservidor.errors.DepartmentException;
import daniel.marina.proyectoparadweservidor.model.Department;
import daniel.marina.proyectoparadweservidor.model.Worker;
import daniel.marina.proyectoparadweservidor.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentException.DepartmentNotFoundException(
                        "El departamento con id " + id + " no existe."
                ));
    }

    public List<Department> findByNameLike(String name) {
        return departmentRepository.findDepartmentsByNameContainsIgnoreCase(name);
    }

    public void delete(UUID id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentException.DepartmentNotFoundException(
                        "El deoartamento con id " + id + " no existe."
                ));
        departmentRepository.delete(department);
    }

    public Department create(Department department) {
        Optional<Department> w = departmentRepository.findById(department.getId());
        if (w.isPresent()) {
            throw new DepartmentException.DepartmentBadRequestException(
                    "El departamento ya existe"
            );
        }
        return departmentRepository.save(department);
    }

    public Department update(UUID id, Department department) {
        Department updated = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentException.DepartmentNotFoundException(
                        "El departamento con id " + id + " no existe."
                ));

        updated.setName(department.getName());
        updated.setDescription(department.getDescription());
        updated.setNumber(department.getNumber());

        return departmentRepository.save(updated);
    }

    public Department patch(UUID id, Department department) {
        Department patch = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentException.DepartmentNotFoundException(
                        "El departamento con id " + id + " no existe."
                ));

        if (department.getName() != null) patch.setName(department.getName());
        if (department.getDescription() != null) patch.setDescription(department.getDescription());
        if (department.getNumber() != null) patch.setNumber(department.getNumber());
        return departmentRepository.save(patch);

    }

}
