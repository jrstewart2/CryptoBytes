package stewart.jonathan.CryptoBytes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stewart.jonathan.CryptoBytes.model.Portfolio;
import stewart.jonathan.CryptoBytes.repository.PortfolioRepository;

import java.util.List;

@Configuration
public class PortfolioConfig {

    @Bean
    CommandLineRunner commandLineRunner(PortfolioRepository portfolioRepository) {
        return args -> {
            Portfolio btc = new Portfolio(
                    "BTC",
                    "Bitcoin",
                    0.123
            );
            Portfolio eth = new Portfolio(
                    "ETH",
                    "Ethereum",
                    0.45
            );
            portfolioRepository.saveAll(List.of(btc, eth));
        };
    }
}
