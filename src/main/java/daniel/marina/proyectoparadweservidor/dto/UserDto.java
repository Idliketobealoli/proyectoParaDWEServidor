package daniel.marina.proyectoparadweservidor.dto;

import daniel.marina.proyectoparadweservidor.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String email;
    private Role role;
}

