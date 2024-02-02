package daniel.marina.proyectoparadweservidor.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Table
public class User {
    @Id
    private UUID id = null;
    private String userName;
    private String userPassword;
    private Role role;
}

