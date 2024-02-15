package daniel.marina.proyectoparadweservidor.mappers;

import daniel.marina.proyectoparadweservidor.dto.worker.WorkerRequestDto;
import daniel.marina.proyectoparadweservidor.dto.worker.WorkerResponseDto;
import daniel.marina.proyectoparadweservidor.model.Worker;
import daniel.marina.proyectoparadweservidor.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WorkerMapper {
    private final DepartmentService departmentService;

    public WorkerResponseDto toDto(Worker worker){
        return new WorkerResponseDto(
                worker.getId(),
                worker.getName(),
                worker.getEmail(),
                worker.getPhone(),
                departmentService.findById(worker.getDepartmentId())
        );
    }

    public List<WorkerResponseDto> toDto (List<Worker> workers){
        return workers.stream().map(this::toDto).toList();
    }




    public Worker toModel(WorkerRequestDto workerRequestDto) {
        return new Worker(
                UUID.randomUUID(),
                workerRequestDto.getFirstName() + " " + workerRequestDto.getLastName(),
                workerRequestDto.getEmail(),
                workerRequestDto.getPhone(),
                workerRequestDto.getDepartment().getId()
        );
    }
}
