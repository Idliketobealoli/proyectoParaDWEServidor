package daniel.marina.proyectoparadweservidor.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoWithToken {
    private UserDto info;
    private String token;
}
