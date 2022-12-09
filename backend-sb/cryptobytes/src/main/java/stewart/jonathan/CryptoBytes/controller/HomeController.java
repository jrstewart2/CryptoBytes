package stewart.jonathan.CryptoBytes.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @GetMapping
    public String home() {
        return "Welcome to CryptoBytes";
    }

    @GetMapping("/user")
    @PreAuthorize(value = "USER")
    public String user() {
        return "Welcome user to CryptoBytes";
    }


}
