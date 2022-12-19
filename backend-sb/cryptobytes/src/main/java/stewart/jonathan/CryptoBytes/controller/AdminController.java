package stewart.jonathan.CryptoBytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<User> getAllUserProfiles() {
        return userService.getUsers();
    }

    @GetMapping("/getUser/{username}")
    public User getUserProfile(@PathVariable String username) {
        return userService.getProfile(username);
    }

    @GetMapping("/getUserPortfolio/{username}")
    public List<Crypto> getPortfolioForUser(@PathVariable String username){
    return userService.getPortfolioForUser(username);
    }

    @PatchMapping("/promote/{username}")
    public void promoteUser(@PathVariable String username){
        userService.promoteUser(username);
    }


}
