package stewart.jonathan.CryptoBytes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stewart.jonathan.CryptoBytes.model.Portfolio;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.PortfolioRepository;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;

    @Autowired
    public TestController(UserRepository userRepository, PortfolioRepository portfolioRepository) {
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/portfolio")
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    @GetMapping("/portfolio/{userId}")
    public List<Portfolio> getPortfolioByUser(@PathVariable String userId){
        Long convertedId = Long.valueOf(userId);
        return portfolioRepository.findByUserId(convertedId);
    }

}
