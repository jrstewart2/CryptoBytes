package stewart.jonathan.CryptoBytes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stewart.jonathan.CryptoBytes.model.Crypto;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
}
