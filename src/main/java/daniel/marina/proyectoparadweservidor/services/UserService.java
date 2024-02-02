package daniel.marina.proyectoparadweservidor.services;

import daniel.marina.proyectoparadweservidor.config.security.jwt.JwtTokenUtils;
import daniel.marina.proyectoparadweservidor.dto.user.*;
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
    private final JwtTokenUtils tokenUtils;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, JwtTokenUtils tokenUtils, UserMapper mapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtils = tokenUtils;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username)
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with email " + username + " not found."));
    }

    public UserDtoWithToken register(UserDtoRegister dto) {
        if (!Objects.equals(dto.getPassword(), dto.getRepeatPassword())) {
            throw new UserException.UserBadRequestException(
                    "Password and repeated password do not match.");
        }
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if (user.isPresent()) {
            throw new UserException.UserBadRequestException(
                    "There's already an account linked to this email.");
        }

        User saved = repository.save(new User(null, dto.getEmail(), dto.getPassword(), Role.USER));
        return new UserDtoWithToken(
                mapper.toDto(saved),
                tokenUtils.create(saved)
        );
    }

    public UserDtoWithToken create(UserDtoCreate dto) {
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if (user.isPresent()) {
            throw new UserException.UserBadRequestException(
                    "There's already an account linked to this email.");
        }

        User saved = repository.save(new User(null, dto.getEmail(), dto.getPassword(), dto.getRole()));
        return new UserDtoWithToken(
                mapper.toDto(saved),
                tokenUtils.create(saved)
        );
    }

    public List<UserDto> listUsers() {
        return mapper.toDto(repository.findAll());
    }

    public List<UserDto> findUsersByRole(Role role) {
        return mapper.toDto(repository.findAllByRole(role));
    }

    public UserDto findUserByEmail(String email) {
        User user = repository.findByUserName(email)
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with email " + email + " not found."));
        return mapper.toDto(user);
    }

    public UserDto findUserById(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new UserException.UserNotFoundException(
                "User with ID " + id + " not found."));
        return mapper.toDto(user);
    }

    public UserDto update(UserDtoUpdate dto) {
        User user = repository.findByUserName(dto.getEmail())
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with email " + dto.getEmail() + " not found."));

        if (!Objects.equals(user.getUserPassword(), dto.getPassword())) {
            throw new UserException.UserBadRequestException(
                    "Incorrect password.");
        }

        User saved = repository.save(new User(user.getId(), user.getUsername(), dto.getNewPassword(), user.getRole()));
        return mapper.toDto(saved);
    }

    public UserDto delete(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserException.UserNotFoundException(
                        "User with ID " + id + " not found."));

        repository.deleteById(id);
        return mapper.toDto(user);
    }
}
