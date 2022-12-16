package stewart.jonathan.CryptoBytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasAuthority('ADMIN')")
    //@PreAuthorize("authentication.scope == 'ADMIN'")
    //@PreAuthorize("authentication.role == 'ADMIN'")
    //@PreAuthorize("principal.authorities.contains('ADMIN')")
    //@PreAuthorize("principal.authorities == 'ADMIN'")
    //@PreAuthorize("principal.authorities.role == 'ADMIN'")
    //@PreAuthorize("principal.authorities[0] == 'ADMIN'")

    public List<User> getAllUserProfiles() {

        return userService.getUsers();
    }

    @GetMapping("/{username}")
    public List<Crypto> getPortfolioForUser(@PathVariable String username){
    return userService.getPortfolioForUser(username);
    }
}
