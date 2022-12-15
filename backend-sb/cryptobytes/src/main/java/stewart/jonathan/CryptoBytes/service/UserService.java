package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.CryptoRepository;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

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

    public User getProfile(String username){
        return getUsers().stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + username + " not found"));
    }

    public List<Crypto> getPortfolioForUser(String username){
        return getProfile(username).getCryptos();
    }

    public Crypto getCryptoFromPortfolio(String symbol, List<Crypto> portfolio){
        return portfolio.stream()
                .filter(c -> symbol.equals(c.getSymbol()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Crypto with symbol " + symbol + " not found in your portfolio"));

    }

    @Transactional
    public void addCryptoToPortfolio(String username, Crypto crypto) {
        User user = getProfile(username);
        List<Crypto> userPortfolio = user.getCryptos();
        crypto.setUser(user);
        userPortfolio.add(crypto);
        user.setCryptos(userPortfolio);
        userRepository.save(user);
    }

    public Crypto updateCoinInPortfolio(String username, String cryptoSymbol, Crypto crypto) {
        User user = getProfile(username);
        Crypto coin = getCryptoFromPortfolio(cryptoSymbol, user.getCryptos());
        coin.setCoins(crypto.getCoins());
        userRepository.save(user);
        return coin;
    }

    public void deleteCoinFromPortfolio(String username, String cryptoSymbol) {
        User user = getProfile(username);
        List<Crypto> portfolio = user.getCryptos();

        Crypto coin = getCryptoFromPortfolio(cryptoSymbol, portfolio);
        portfolio.remove(coin);
        coin.setUser(null);
        user.setCryptos(portfolio);
        userRepository.save(user);
    }

    public void registerNewUser(User user){
        userRepository.save(user);
    }
}
