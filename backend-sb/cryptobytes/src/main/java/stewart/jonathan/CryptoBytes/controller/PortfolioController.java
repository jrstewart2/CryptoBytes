package stewart.jonathan.CryptoBytes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import stewart.jonathan.CryptoBytes.model.Portfolio;
import stewart.jonathan.CryptoBytes.service.PortfolioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "http://localhost:3000")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<Portfolio> getPortfolio() {
        return portfolioService.getPortfolio();
    }

    @GetMapping("/{id}")
    public Portfolio getPortfolioById(@PathVariable long id) {
        return portfolioService.getPortfolioById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void addCoinToPortfolio(@RequestBody Portfolio portfolio) {
        portfolioService.addCoin(portfolio);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public void updateCoin(@PathVariable Long id,
                           @RequestBody Portfolio portfolio) {
        portfolioService.updateCoin(id, portfolio);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void removeCoinFromPortfolio(@PathVariable Long id){
        portfolioService.removeCoin(id);
    }


}
