package daniel.marina.proyectoparadweservidor.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoRegister {
    private String email;
    private String password;
    private String repeatPassword;
}

