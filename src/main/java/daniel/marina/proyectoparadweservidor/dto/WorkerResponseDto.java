package daniel.marina.proyectoparadweservidor.dto;

import daniel.marina.proyectoparadweservidor.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class WorkerResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private UUID departmentId; // TODO shoud be department objetct not id
}
