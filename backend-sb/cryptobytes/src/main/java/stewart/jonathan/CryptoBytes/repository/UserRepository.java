package stewart.jonathan.CryptoBytes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import stewart.jonathan.CryptoBytes.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}