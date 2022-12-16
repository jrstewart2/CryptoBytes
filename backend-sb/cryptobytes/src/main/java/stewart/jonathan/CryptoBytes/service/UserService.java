package stewart.jonathan.CryptoBytes.service;

import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    List<User> getUsers();

    String getUsername(String authenticatedUsername);

    User getProfile(String username);

    List<Crypto> getPortfolioForUser(String username);

    Crypto getCryptoFromPortfolio(String symbol, List<Crypto> portfolio);

    Crypto getSingleCoin(String username, String symbol);

    @Transactional
    void addCryptoToPortfolio(String username, Crypto crypto);

    Crypto updateCoinInPortfolio(String username, String cryptoSymbol, Crypto crypto);

    void deleteCoinFromPortfolio(String username, String cryptoSymbol);

    boolean checkIfUsernameExists(String username);

    boolean checkIfEmailExists(String email);

    void registerNewUser(User user);

    void updateEmail(String username, User user);
}
