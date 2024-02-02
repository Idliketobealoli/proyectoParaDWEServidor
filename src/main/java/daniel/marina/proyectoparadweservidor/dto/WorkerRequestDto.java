package daniel.marina.proyectoparadweservidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
