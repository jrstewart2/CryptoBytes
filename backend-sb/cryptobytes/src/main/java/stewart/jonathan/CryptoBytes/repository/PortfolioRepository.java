package stewart.jonathan.CryptoBytes.repository;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import stewart.jonathan.CryptoBytes.model.Portfolio;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByUserId(Long userId);

    @Transactional
    void deleteByUserId(Long userId);

}
