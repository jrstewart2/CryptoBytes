package stewart.jonathan.CryptoBytes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    @GetMapping("/loggedin")
    public String testlog (Principal principal){
        return "Hello " + principal.toString();
    }

}

