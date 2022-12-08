package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.Utilities.PasswordHasher;
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

    public Optional<User> getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            return userRepository.findUserByUsername(username);
        } else {
            throw new IllegalArgumentException("Username " + username + " not found.");
        }
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            return userRepository.findUserByUsername(email);
        } else {
            throw new IllegalArgumentException("Email " + email + " not found.");
        }
    }

    public void registerNewUser(User user) {
        Optional<User> optionalUsername = userRepository.findUserByUsername(user.getUsername());
        Optional<User> optionalEmail = userRepository.findUserByEmail(user.getEmail());
        if (optionalUsername.isPresent()) {
            throw new IllegalArgumentException("Username already registered");
        } else if (optionalEmail.isPresent()) {
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
