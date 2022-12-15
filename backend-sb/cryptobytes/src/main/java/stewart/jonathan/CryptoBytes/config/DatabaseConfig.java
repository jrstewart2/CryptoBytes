package stewart.jonathan.CryptoBytes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.CryptoRepository;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import java.util.List;


/*
FOR DEVELOPMENT ONLY
 */


@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        CryptoRepository cryptoRepository,
                                        PasswordEncoder encoder) {
        return args -> {
            // CREATE 2 USERS
            User nonAdmin = new User("user","user@email.com",encoder.encode("password"),"USER");
            User admin = new User("admin","admin@email.com",encoder.encode("password"),"ADMIN");

           // ADD CRYPTO FOR USER
            Crypto userBtc = new Crypto("BTC","Bitcoin",0.123,nonAdmin);
            Crypto userEth = new Crypto("ETH","Ethereum",0.45,nonAdmin);
            List<Crypto> userCrypto = List.of(userBtc, userEth);
            nonAdmin.setCryptos(userCrypto);
            userRepository.save(nonAdmin);

            // ADD CRYPTO FOR ADMIN
            Crypto adminBtc = new Crypto("BTC","Bitcoin",7.23,admin);
            Crypto adminLrc = new Crypto("LRC","Loopring",567.2,admin);
            Crypto adminImx = new Crypto("IMX","Immutable X",34.12,admin);
            List<Crypto> adminCrypto = List.of(adminBtc,adminImx,adminLrc);
            admin.setCryptos(adminCrypto);
            userRepository.save(admin);

        };
    }
}
