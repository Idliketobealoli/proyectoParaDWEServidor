package daniel.marina.proyectoparadweservidor.mappers;

import daniel.marina.proyectoparadweservidor.dto.WorkerRequestDto;
import daniel.marina.proyectoparadweservidor.dto.WorkerResponseDto;
import daniel.marina.proyectoparadweservidor.model.Worker;

import java.util.List;
import java.util.UUID;

// TODO toDto and toModel will change when dto changes
public class WorkerMapper {
    public static WorkerResponseDto toDto(Worker worker){
        return new WorkerResponseDto(
                worker.getId(),
                worker.getName(),
                worker.getEmail(),
                worker.getPhone(),
                worker.getDepartmentId()
        );
    }

    public static List<WorkerResponseDto> toDto (List<Worker> workers){
        return workers.stream().map(WorkerMapper::toDto).toList();
    }




    public static Worker toModel(WorkerRequestDto workerRequestDto) {
        return new Worker(
                UUID.randomUUID(),
                workerRequestDto.getFirstName() + " " + workerRequestDto.getLastName(),
                workerRequestDto.getEmail(),
                workerRequestDto.getPhone(),
                workerRequestDto.getDepartmentId()
        );
    }
}
