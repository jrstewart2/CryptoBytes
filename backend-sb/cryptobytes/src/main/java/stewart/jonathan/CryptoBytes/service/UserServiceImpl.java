package stewart.jonathan.CryptoBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;
import stewart.jonathan.CryptoBytes.repository.CryptoRepository;
import stewart.jonathan.CryptoBytes.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CryptoRepository cryptoRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CryptoRepository cryptoRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.cryptoRepository = cryptoRepository;
        this.encoder = encoder;
    }

    @Override
    public String getUsername(String authenticatedUsername){
        User user = getProfile(authenticatedUsername);
        return user.getUsername();
    }

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getProfile(String username){
        return getUsers().stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + username + " not found"));
    }

    @Override
    public List<Crypto> getPortfolioForUser(String username){
        return getProfile(username).getCryptos();
    }

    @Override
    public Crypto getCryptoFromPortfolio(String symbol, List<Crypto> portfolio){
        return portfolio.stream()
                .filter(c -> symbol.equals(c.getSymbol()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Crypto with symbol " + symbol + " not found in your portfolio"));

    }

    @Override
    @Transactional
    public void addCryptoToPortfolio(String username, Crypto crypto) {
        User user = getProfile(username);
        List<Crypto> userPortfolio = user.getCryptos();
        crypto.setUser(user);
        userPortfolio.add(crypto);
        user.setCryptos(userPortfolio);
        userRepository.save(user);
    }

    @Override
    public Crypto updateCoinInPortfolio(String username, String cryptoSymbol, Crypto crypto) {
        User user = getProfile(username);
        Crypto coin = getCryptoFromPortfolio(cryptoSymbol, user.getCryptos());
        coin.setCoins(crypto.getCoins());
        userRepository.save(user);
        return coin;
    }

    @Override
    public void deleteCoinFromPortfolio(String username, String cryptoSymbol) {
        User user = getProfile(username);
        List<Crypto> portfolio = user.getCryptos();

        Crypto coin = getCryptoFromPortfolio(cryptoSymbol, portfolio);
        portfolio.remove(coin);
        coin.setUser(null);
        user.setCryptos(portfolio);
        userRepository.save(user);
    }

    public boolean checkIfUsernameExists(String username) {
        Optional<User> checkUsername = userRepository.findByUsername(username);
        return checkUsername.isPresent();
    }

    public boolean checkIfEmailExists(String email) {
        Optional<User> checkEmail = userRepository.findByEmail(email);
        return checkEmail.isPresent();
    }

    @Override
    public void registerNewUser(User user){
        if (checkIfUsernameExists(user.getUsername())){
            throw new IllegalArgumentException("Username unavailable. Please choose another");
        } else if (checkIfEmailExists(user.getEmail())){
            throw new IllegalArgumentException("Email already registered");
        } else if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null){
            throw new IllegalArgumentException("You must provide a username, email and password to register");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    @Override
    public void updateEmail(String username, User user){
        if (checkIfUsernameExists(username)) {
            if (user.getEmail() != null) {
                User currentUserDetails = getProfile(username);
                currentUserDetails.setEmail(user.getEmail());
                userRepository.save(currentUserDetails);
            } else {
                throw new IllegalArgumentException("No email address found in request - user details not updated");
            }
        }
    }

    @Override
    public Crypto getSingleCoin(String username, String symbol){
        List<Crypto> portfolio = getPortfolioForUser(username);
        return getCryptoFromPortfolio(symbol, portfolio);
    }
}
