package daniel.marina.proyectoparadweservidor.mappers;

import daniel.marina.proyectoparadweservidor.dto.DepartmentRequestDto;
import daniel.marina.proyectoparadweservidor.dto.DepartmentResponseDto;
import daniel.marina.proyectoparadweservidor.model.Department;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DepartmentMapper {
    public DepartmentResponseDto toDto(Department department){
        return new DepartmentResponseDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getNumber()
        );
    }

    public List<DepartmentResponseDto> toDto (List<Department> departments){
        return departments.stream().map(this::toDto).toList();
    }

    public Department toModel(DepartmentRequestDto departmentRequestDto) {
        return new Department(
                UUID.randomUUID(),
                departmentRequestDto.getName(),
                departmentRequestDto.getDescription(),
                departmentRequestDto.getNumber()
        );
    }

    /* public Department toModel(UUID id) {
        return new Department(
                id,
               null,
                null,
                0
        );
    } */
}
