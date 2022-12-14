package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.CryptoRepository;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;

    @Autowired
    public UserService(UserRepository userRepository, CryptoRepository cryptoRepository) {
        this.userRepository = userRepository;
        this.cryptoRepository = cryptoRepository;
    }

    public User getProfile(){
        return 
    }
}
