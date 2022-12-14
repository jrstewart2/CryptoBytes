package stewart.jonathan.CryptoBytes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import stewart.jonathan.CryptoBytes.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}