package stewart.jonathan.CryptoBytes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                                        PortfolioRepository portfolioRepository,
                                        PasswordEncoder encoder) {
        return args -> {
            User user = new User(
                    "user",
                    "user@email.com",
                    encoder.encode("password"),
                    "USER"
            );
            User admin = new User(
                    "admin",
                    "admin@email.com",
                    encoder.encode("password"),
                    "ADMIN"
            );
            userRepository.saveAll(List.of(user, admin));

            // ADD CRYPTO FOR USER
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
            btc.setUser(user);
            eth.setUser(user);
            portfolioRepository.saveAll(List.of(btc, eth));

            //ADD CRYPTO FOR ADMIN
            Portfolio adminBtc = new Portfolio(
                    "BTC",
                    "Bitcoin",
                    17.23
            );
            Portfolio adminLrc = new Portfolio(
                    "LRC",
                    "Loopring",
                    567.2
            );
            Portfolio adminImx = new Portfolio(
                    "IMX",
                    "Immutable X",
                    34.12
            );
            adminBtc.setUser(admin);
            adminLrc.setUser(admin);
            adminImx.setUser(admin);
        };
    }
}
