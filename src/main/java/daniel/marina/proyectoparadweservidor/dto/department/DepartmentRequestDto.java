package daniel.marina.proyectoparadweservidor.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {
    private String name;
    private String description;
    private Integer number;
}
