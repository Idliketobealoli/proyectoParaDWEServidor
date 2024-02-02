package daniel.marina.proyectoparadweservidor.mappers;

import daniel.marina.proyectoparadweservidor.dto.DepartmentRequestDto;
import daniel.marina.proyectoparadweservidor.dto.DepartmentResponseDto;
import daniel.marina.proyectoparadweservidor.model.Department;

import java.util.List;
import java.util.UUID;

public class DepartmentMapper {
    public static DepartmentResponseDto toDto(Department department){
        return new DepartmentResponseDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getNumber()
        );
    }

    public static List<DepartmentResponseDto> toDto (List<Department> departments){
        return departments.stream().map(DepartmentMapper::toDto).toList();
    }

    public static Department toModel(DepartmentRequestDto departmentRequestDto) {
        return new Department(
                UUID.randomUUID(),
                departmentRequestDto.getName(),
                departmentRequestDto.getDescription(),
                departmentRequestDto.getNumber()
        );
    }

    public static Department toModel(UUID id) {
        return new Department(
                id,
               null,
                null,
                0
        );
    }
}
