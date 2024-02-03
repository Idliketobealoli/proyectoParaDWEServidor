package daniel.marina.proyectoparadweservidor.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentRequestDto {
    private String name;
    private String description;
    private Integer number;
}
