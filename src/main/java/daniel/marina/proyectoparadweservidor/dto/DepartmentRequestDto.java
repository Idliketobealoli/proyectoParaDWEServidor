package daniel.marina.proyectoparadweservidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentRequestDto {
    private String name;
    private String description;
    private int departmentNumber;
}
