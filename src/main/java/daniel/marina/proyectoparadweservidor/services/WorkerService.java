package daniel.marina.proyectoparadweservidor.services;

import daniel.marina.proyectoparadweservidor.errors.DepartmentException;
import daniel.marina.proyectoparadweservidor.errors.WorkerException;
import daniel.marina.proyectoparadweservidor.model.Department;
import daniel.marina.proyectoparadweservidor.model.Worker;
import daniel.marina.proyectoparadweservidor.repositories.DepartmentRepository;
import daniel.marina.proyectoparadweservidor.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final DepartmentRepository departmentRepository;

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(UUID id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new WorkerException.WorkerNotFoundException(
                        "El trabajador con id " + id + " no existe."
                ));
    }

    public List<Worker> findByDepartment(UUID depatmentId) {
        return workerRepository.findWorkersByDepartmentId(depatmentId);
    }

    public List<Worker> findByNameLike(String name) {
        return workerRepository.findWorkersByNameContainsIgnoreCase(name);
    }

    public void delete(UUID id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerException.WorkerNotFoundException(
                        "El trabajador con id " + id + " no existe."
                ));
        workerRepository.delete(worker);
    }

    public Worker create(Worker worker) {
        Optional <Worker> w = workerRepository.findById(worker.getId());
        if (w.isPresent()) {
            throw new WorkerException.WorkerBadRequestException(
                    "El trabajador ya existe"
            );
        }
        Department department = getDepartment(worker);
        if (department != null) {
            return workerRepository.save(worker);
        } else {
            throw new DepartmentException.DepartmentNotFoundException(
                    "El id " + worker.getDepartmentId() + " no se corresponde con el de ningún departamento."
            );
        }
    }

    public Worker update(UUID id, Worker worker) {
        Worker updated = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerException.WorkerNotFoundException(
                        "El trabajador con id " + id + " no existe."
                ));
        Department department = getDepartment(worker);

        if (department != null) {
            updated.setName(worker.getName());
            updated.setEmail(worker.getEmail());
            updated.setPhone(worker.getPhone());
            updated.setDepartmentId(worker.getDepartmentId());
            return workerRepository.save(updated);
        } else {
            throw new DepartmentException.DepartmentNotFoundException(
                    "El id " + worker.getDepartmentId() + " no se corresponde con el de ningún departamento."
            );
        }
    }

    public Worker patch(UUID id, Worker worker) {
        Worker patch = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerException.WorkerNotFoundException(
                        "El trabajador con id " + id + " no existe."
                ));

        Department department = getDepartment(worker);

        if (department != null) {
            if (worker.getName() != null) patch.setName(worker.getName());
            if (worker.getEmail() != null) patch.setEmail(worker.getEmail());
            if (worker.getPhone() != null) patch.setPhone(worker.getPhone());
            if (worker.getDepartmentId() != null) patch.setDepartmentId(worker.getDepartmentId());
            return workerRepository.save(patch);
        } else {
            throw new DepartmentException.DepartmentNotFoundException(
                    "El id " + worker.getDepartmentId() + " no se corresponde con el de ningún departamento."
            );
            }
    }


    private Department getDepartment(Worker worker) {
        Department department = null;
        if (worker.getDepartmentId() != null) {
            department = departmentRepository.findById(worker.getDepartmentId())
                    .orElseThrow(() -> new DepartmentException.DepartmentNotFoundException(
                            "El departamento con id " + worker.getDepartmentId() + " no existe."
                    ));
        }
        return department;
    }

}
