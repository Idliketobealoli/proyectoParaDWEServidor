package daniel.marina.proyectoparadweservidor.services;

import daniel.marina.proyectoparadweservidor.errors.WorkerException;
import daniel.marina.proyectoparadweservidor.model.Worker;
import daniel.marina.proyectoparadweservidor.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public Worker findById(UUID id) throws WorkerException {
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



}
