package stewart.jonathan.CryptoBytes.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.service.CustomUserDetailService;
import stewart.jonathan.CryptoBytes.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "http://localhost:3000")
@PreAuthorize("#username == authentication.name")
public class PortfolioController {

    private final UserService userService;

    @Autowired
    public PortfolioController(UserService userService) {
        this.userService = userService;
    }

    public boolean AuthorizeAccess(String username){
        return true;
    }


    @GetMapping("/{username}")
    public List<Crypto> getPortfolioForUser(@PathVariable String username){
        return userService.getPortfolioForUser(username);
    }

    @GetMapping("/{username}/{symbol}")
    public Crypto getSingleCoin(@PathVariable String username,
                                @PathVariable String symbol){
        return userService.getSingleCoin(username, symbol);
    }

    @PostMapping("/{username}")
    public void addNewCrypto(@PathVariable String username,
                             @RequestBody Crypto crypto) {
        userService.addCryptoToPortfolio(username, crypto);
    }

    @PatchMapping("/{username}/{cryptoSymbol}")
    public Crypto updateCoin(@PathVariable String username,
                             @PathVariable String cryptoSymbol,
                             @RequestBody Crypto crypto) {
        return userService.updateCoinInPortfolio(username, cryptoSymbol, crypto);
    }

    @DeleteMapping("/{username}/{cryptoSymbol}")
    public void deleteCoin(@PathVariable String username,
                           @PathVariable String cryptoSymbol){
        userService.deleteCoinFromPortfolio(username, cryptoSymbol);
    }
}
