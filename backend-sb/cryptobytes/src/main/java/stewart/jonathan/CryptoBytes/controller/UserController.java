package stewart.jonathan.CryptoBytes.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUserProfiles() {
        return userService.getUsers();
    }

    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name")
    public User getUserProfileByUsername(@PathVariable String username){
        return userService.getProfile(username);
    }

}
