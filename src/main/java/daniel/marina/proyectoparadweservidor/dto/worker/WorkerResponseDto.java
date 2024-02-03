package daniel.marina.proyectoparadweservidor.dto.worker;

import daniel.marina.proyectoparadweservidor.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private Department department;
}
