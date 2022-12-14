package stewart.jonathan.CryptoBytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getCryptoByUserId(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @GetMapping("/portfolio/{id}")
    public List<Crypto> getPortfolioForUser(@PathVariable Long id){
        Optional<User> user = getCryptoByUserId(id);
        return user.get().getCryptos();
    }
}
