package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.CryptoRepository;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;

    @Autowired
    public UserService(UserRepository userRepository, CryptoRepository cryptoRepository) {
        this.userRepository = userRepository;
        this.cryptoRepository = cryptoRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getProfile(Long id){
        return getUsers().stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
    }

    public List<Crypto> getPortfolioForUser(Long id){
        return getProfile(id).getCryptos();
    }

    public Crypto getCryptoFromPortfolio(String symbol, List<Crypto> portfolio){
        return portfolio.stream()
                .filter(c -> symbol.equals(c.getSymbol()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Crypto with symbol " + symbol + " not found in your portfolio"));

    }

    @Transactional
    public void addCryptoToPortfolio(Long id, Crypto crypto) {
        User user = getProfile(id);
        List<Crypto> userPortfolio = user.getCryptos();
        userPortfolio.add(crypto);
        user.addCryptos(userPortfolio);
        userRepository.save(user);
    }

    public Crypto updateCoinInPortfolio(long id, String cryptoSymbol, Crypto crypto) {
        User user = getProfile(id);
        Crypto coin = getCryptoFromPortfolio(cryptoSymbol, user.getCryptos());
        coin.setCoins(crypto.getCoins());
        userRepository.save(user);
        return coin;
    }
}
