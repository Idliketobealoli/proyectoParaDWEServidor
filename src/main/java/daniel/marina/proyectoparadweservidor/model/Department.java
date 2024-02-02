package daniel.marina.proyectoparadweservidor.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Department {
    @Id
    private UUID id = null;
    private String name;
    private String description;
    private int number;
}
