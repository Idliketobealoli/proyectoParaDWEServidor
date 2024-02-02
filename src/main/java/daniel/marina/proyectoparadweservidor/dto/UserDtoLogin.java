package daniel.marina.proyectoparadweservidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoLogin {
    private String email;
    private String password;
}
