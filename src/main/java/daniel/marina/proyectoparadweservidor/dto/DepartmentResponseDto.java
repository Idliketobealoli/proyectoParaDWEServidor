package daniel.marina.proyectoparadweservidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class DepartmentResponseDto {
    private UUID id;
    private String name;
    private String description;
    private int departmentNumber;
}
