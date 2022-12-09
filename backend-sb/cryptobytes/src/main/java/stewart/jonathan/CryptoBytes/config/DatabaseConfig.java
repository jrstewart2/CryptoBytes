package stewart.jonathan.CryptoBytes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stewart.jonathan.CryptoBytes.model.Portfolio;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.UserRepository;
import stewart.jonathan.CryptoBytes.repository.PortfolioRepository;

import java.util.List;


/*
FOR DEVELOPMENT ONLY
 */


@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        PortfolioRepository portfolioRepository) {
        return args -> {
            User user = new User(
                    "1",
                    "user",
                    "user@email.com",
                    "password",
                    "USER"
            );
            User admin = new User(
                    "2",
                    "admin",
                    "admin@email.com",
                    "password",
                    "ADMIN"
            );
            userRepository.saveAll(List.of(user, admin));

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
