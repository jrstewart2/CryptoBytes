package stewart.jonathan.CryptoBytes.controller;

import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/findByUsername")
    public User findByUsername(@RequestBody User user){
        return userService.findByUsername(user.getUsername());
    }

    @GetMapping("/emailSearch")
    public User getUserByEmail(@RequestBody User user) {
        return userService.findByEmail(user.getEmail());
    }

    @PatchMapping("/makeAdmin")
    public User updateRole(@RequestBody Long id) {
        return userService.updateRole(id);
    }

    @DeleteMapping
    public String deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }

}
