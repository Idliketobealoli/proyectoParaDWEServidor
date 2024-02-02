package daniel.marina.proyectoparadweservidor.repositories;

import daniel.marina.proyectoparadweservidor.model.Role;
import daniel.marina.proyectoparadweservidor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByRole(Role role);
    Optional<User> findByUserName(String userName);
}
