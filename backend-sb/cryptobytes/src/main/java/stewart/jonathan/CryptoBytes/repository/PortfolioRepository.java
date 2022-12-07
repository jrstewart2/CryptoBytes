package stewart.jonathan.CryptoBytes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stewart.jonathan.CryptoBytes.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, String> {


}
