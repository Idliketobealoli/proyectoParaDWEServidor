package daniel.marina.proyectoparadweservidor.dto;

import daniel.marina.proyectoparadweservidor.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoCreate {
    private String email;
    private String password;
    private Role role;
}
