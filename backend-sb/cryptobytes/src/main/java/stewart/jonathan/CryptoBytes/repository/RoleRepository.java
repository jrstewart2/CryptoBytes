package stewart.jonathan.CryptoBytes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stewart.jonathan.CryptoBytes.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
