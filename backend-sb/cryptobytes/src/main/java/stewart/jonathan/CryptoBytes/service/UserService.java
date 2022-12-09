package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.utilities.PasswordHasher;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return getUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Username " + username + " is not registered"));
    }

    public User findByEmail(String email) {
        return getUsers().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Email " + email + " is not registered"));
    }


    public void registerNewUser(User user) {
        User optionalUsername = userRepository.findByUsername(user.getUsername());
        User optionalEmail = userRepository.findByEmail(user.getEmail());
        if (optionalUsername != null) {
            throw new IllegalArgumentException("Username already registered");
        } else if (optionalEmail != null) {
            throw new IllegalArgumentException("Email already registered");
        }
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordHasher.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

    }

    public void updateUserDetails(User user) {
        //authenticate first
    }

    public void deleteUser(String id) {

    }
}
