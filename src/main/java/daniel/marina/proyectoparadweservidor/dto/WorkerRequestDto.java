package daniel.marina.proyectoparadweservidor.dto;

import daniel.marina.proyectoparadweservidor.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WorkerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Department department;

}
