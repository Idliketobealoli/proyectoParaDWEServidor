package daniel.marina.proyectoparadweservidor.mappers;

import daniel.marina.proyectoparadweservidor.dto.user.UserDto;
import daniel.marina.proyectoparadweservidor.dto.user.UserDtoRegister;
import daniel.marina.proyectoparadweservidor.model.Role;
import daniel.marina.proyectoparadweservidor.model.User;
import daniel.marina.proyectoparadweservidor.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserMapper {
    public static User fromDtoRegister(UserDtoRegister dto, UserRepository repository) {
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if (user.isPresent() || !Objects.equals(dto.getPassword(), dto.getRepeatPassword())) {
            return null;
        }
        else return new User(null, dto.getEmail(), dto.getPassword(), Role.USER);
    }

    public static UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getRole()
        );
    }

    public static List<UserDto> toDto(List<User> entities) {
        return entities.stream().map(UserMapper::toDto).toList();
    }
}
