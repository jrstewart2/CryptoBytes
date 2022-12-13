package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.model.Portfolio;
import stewart.jonathan.CryptoBytes.repository.PortfolioRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class PortfolioService{

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<Portfolio> getPortfolio() {
        return portfolioRepository.findAll();
    }

    public Portfolio getPortfolioById(long id) {
        return getPortfolio().stream().filter(stream -> stream.getSymbol().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Coin not found in your portfolio"));
    }

    public void addCoin(Portfolio portfolio){
        portfolioRepository.save(portfolio);
    }

    @Transactional
    public void updateCoin(long id, Portfolio portfolio){
        Portfolio coin = portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coin " + id + "not found in your portfolio"));
        if (portfolio.getSymbol() != null){
            coin.setSymbol(portfolio.getSymbol());
        }
        if (portfolio.getName() != null){
            coin.setName(portfolio.getName());
        }
        if (portfolio.getAmount() != null) {
            coin.setAmount(portfolio.getAmount());
        }
    }

    public void removeCoin(long id){
        if (portfolioRepository.existsById(id)){
            portfolioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Coin " + id + " not found in your portfolio");
        }
    }
}

