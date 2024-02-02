package daniel.marina.proyectoparadweservidor.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Table
public class Department {
    @Id
    private UUID id = null;
    private String name;
    private String description;
    private int number;
}
