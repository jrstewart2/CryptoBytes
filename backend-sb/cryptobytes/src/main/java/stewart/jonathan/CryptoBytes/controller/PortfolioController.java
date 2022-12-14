package stewart.jonathan.CryptoBytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final UserService userService;

    @Autowired
    public PortfolioController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")

    public List<Crypto> getPortfolioForUser(@PathVariable Long id){
        return userService.getPortfolioForUser(id);
    }

    @PostMapping("/{id}")
    public void addNewCrypto(@PathVariable Long id,
                             @RequestBody Crypto crypto) {
        userService.addCryptoToPortfolio(id, crypto);
    }

    @PatchMapping("/{id}/{cryptoSymbol}")
    public Crypto updateCoin(@PathVariable long id,
                             @PathVariable String cryptoSymbol,
                             @RequestBody Crypto crypto) {
        return userService.updateCoinInPortfolio(id, cryptoSymbol, crypto);
    }

    @DeleteMapping("/{id}/{cryptoSymbol}")
    public void deleteCoin(@PathVariable long id,
                           @PathVariable String cryptoSymbol){
        userService.deleteCoinFromPortfolio(id, cryptoSymbol);
    }
}
