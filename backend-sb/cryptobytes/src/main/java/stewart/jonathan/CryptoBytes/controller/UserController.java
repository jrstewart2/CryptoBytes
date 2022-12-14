package stewart.jonathan.CryptoBytes.controller;

import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getCryptoByUserId(@PathVariable Long id){
        return userService.getProfile(id);
    }

    @GetMapping("/portfolio/{id}")
    public List<Crypto> getPortfolioForUser(@PathVariable Long id){
        return userService.getPortfolioForUser(id);
    }

    @PostMapping("/portfolio/{id}")
    public void addNewCrypto(@PathVariable Long id,
                                @RequestBody Crypto crypto) {
        userService.addCryptoToPortfolio(id, crypto);
    }

    @PatchMapping("/portfolio/{id}/{cryptoSymbol}")
    public Crypto updateCoin(@PathVariable long id,
                             @PathVariable String cryptoSymbol,
                             @RequestBody Crypto crypto) {
        return userService.updateCoinInPortfolio(id, cryptoSymbol, crypto);
    }
}
