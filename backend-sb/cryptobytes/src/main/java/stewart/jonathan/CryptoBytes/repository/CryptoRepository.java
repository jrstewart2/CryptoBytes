package stewart.jonathan.CryptoBytes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;

import java.util.Optional;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    Optional<User> findBySymbol(String symbol);
}
