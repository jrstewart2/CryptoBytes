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
    //@PreAuthorize("#username == authentication.name")
    public List<Crypto> getPortfolioForUser(@PathVariable String username){
        return userService.getPortfolioForUser(username);
    }

    @PostMapping("/{username}")
    //@PreAuthorize("#username == authentication.name")
    public void addNewCrypto(@PathVariable String username,
                             @RequestBody Crypto crypto) {
        userService.addCryptoToPortfolio(username, crypto);
    }

    @PatchMapping("/{username}/{cryptoSymbol}")
    @PreAuthorize("#username == authentication.name")
    public Crypto updateCoin(@PathVariable String username,
                             @PathVariable String cryptoSymbol,
                             @RequestBody Crypto crypto) {
        return userService.updateCoinInPortfolio(username, cryptoSymbol, crypto);
    }

    @DeleteMapping("/{username}/{cryptoSymbol}")
    @PreAuthorize("#username == authentication.name")
    public void deleteCoin(@PathVariable String username,
                           @PathVariable String cryptoSymbol){
        userService.deleteCoinFromPortfolio(username, cryptoSymbol);
    }
}
