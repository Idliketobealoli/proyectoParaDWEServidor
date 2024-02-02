package daniel.marina.proyectoparadweservidor.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class DepartmentResponseDto {
    private UUID id;
    private String name;
    private String description;
    private int number;
}
