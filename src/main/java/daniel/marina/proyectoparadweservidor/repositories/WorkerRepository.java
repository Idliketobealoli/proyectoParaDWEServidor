package daniel.marina.proyectoparadweservidor.repositories;

import daniel.marina.proyectoparadweservidor.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    List<Worker> findWorkersByDepartmentId(UUID id);
    List<Worker> findWorkersByNameContainsIgnoreCase(String name);
}
