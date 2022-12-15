package stewart.jonathan.CryptoBytes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @GetMapping
    public String home() {
        return "Welcome to CryptoBytes";
    }

}
