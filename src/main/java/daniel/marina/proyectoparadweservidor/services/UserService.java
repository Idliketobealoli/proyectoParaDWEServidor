package daniel.marina.proyectoparadweservidor.services;

import daniel.marina.proyectoparadweservidor.dto.UserDto;
import daniel.marina.proyectoparadweservidor.dto.UserDtoCreate;
import daniel.marina.proyectoparadweservidor.dto.UserDtoRegister;
import daniel.marina.proyectoparadweservidor.errors.UserException;
import daniel.marina.proyectoparadweservidor.mappers.UserMapper;
import daniel.marina.proyectoparadweservidor.model.Role;
import daniel.marina.proyectoparadweservidor.model.User;
import daniel.marina.proyectoparadweservidor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username)
                .orElseThrow(() -> new UserException.UserExceptionNotFound(
                        "Usuario con email " + username + " no encontrado."));
    }

    public User register(UserDtoRegister dto) {
        if (!Objects.equals(dto.getPassword(), dto.getRepeatPassword())) {
            throw new UserException.UserExceptionBadRequest(
                    "Password and repeated password do not match.");
        }
        repository.findByUserName(dto.getEmail())
                .orElseThrow(() -> new UserException.UserExceptionBadRequest(
                        "There's already an account linked to this email."));

        return repository.save(new User(null, dto.getEmail(), dto.getPassword(), Role.USER));
    }

    public User create(UserDtoCreate dto) {
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if (user.isPresent()) {
            throw new UserException.UserExceptionBadRequest(
                    "There's already an account linked to this email.");
        }

        return repository.save(new User(null, dto.getEmail(), dto.getPassword(), dto.getRole()));
    }

    public List<UserDto> listUsers() {
        return UserMapper.toDto(repository.findAll());
    }

    public List<UserDto> findUsersByRole(Role role) {
        return UserMapper.toDto(repository.findAllByRole(role));
    }

    public UserDto findUserByEmail(String email) {
        User user = repository.findByUserName(email)
                .orElseThrow(() -> new UserException.UserExceptionNotFound(
                        "Usuario con email " + email + " no encontrado."));
        return UserMapper.toDto(user);
    }

    public UserDto findUserById(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new UserException.UserExceptionNotFound(
                "Usuario con ID " + id + " no encontrado."));
        return UserMapper.toDto(user);
    }
}
