package stewart.jonathan.CryptoBytes.controller;

import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/usernameSearch")
    public Optional<User> getUserByUsername(@RequestBody User user) {
        return userService.getUserByUsername(user.getUsername());
    }

    @GetMapping("/emailSearch")
    public Optional<User> getUserByEmail(@RequestBody User user) {
        return userService.getUserByEmail(user.getEmail());
    }

    @PostMapping("/register")
    public void registerNewUser(@RequestBody User user) {
        userService.registerNewUser(user);
    }

}
