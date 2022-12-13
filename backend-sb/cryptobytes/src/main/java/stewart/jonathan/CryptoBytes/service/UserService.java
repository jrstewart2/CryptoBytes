package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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
        Optional<User> optionalUsername = userRepository.findByUsername(user.getUsername());
        Optional<User> optionalEmail = userRepository.findByEmail(user.getEmail());
        if (optionalUsername.isPresent() || optionalEmail.isPresent()) {
            throw new IllegalArgumentException("Username or Email already registered");
        } else {
//            user.setId(UUID.randomUUID().toString());
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("USER");
            userRepository.save(user);
        }
    }

    public User findById(long id) {
        return getUsers().stream()
                .filter(user -> id == user.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID: " + id + " not found"));
    }

    public User updateRole(long id) {
        User userToBeAdmin = findById(id);
        userToBeAdmin.setRole("ADMIN");
        return userToBeAdmin;
    }

    public User updateEmail(User user) {
        User currentDetails = findByUsername(user.getUsername());
        currentDetails.setEmail(user.getEmail());
        return currentDetails;
    }

    public String deleteUser(User user) {
        if (user.getId() > 0) {
            User userToBeDeleted = findById(user.getId());
            userRepository.delete(userToBeDeleted);
        } else if (user.getUsername() != null) {
            User userToBeDeleted = findByUsername(user.getUsername());
            userRepository.delete(userToBeDeleted);
        } else {
            User userToBeDeleted = findByEmail(user.getEmail());
            userRepository.delete(userToBeDeleted);
        }
        return "User " + user + " was deleted";
    }
}
