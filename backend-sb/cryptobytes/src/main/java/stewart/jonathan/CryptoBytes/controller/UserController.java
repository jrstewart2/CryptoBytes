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
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name")
    public User getUserProfileByUsername(@PathVariable String username){
        return userService.getProfile(username);
    }

    @PatchMapping("/{username}")
    @PreAuthorize("#username == authentication.name")
    public void updateEmailAddress(@PathVariable String username,
                                   @RequestBody User user){
        userService.updateEmail(username, user);
    }

}
