package daniel.marina.proyectoparadweservidor.dto.worker;

import daniel.marina.proyectoparadweservidor.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Department department;

}
