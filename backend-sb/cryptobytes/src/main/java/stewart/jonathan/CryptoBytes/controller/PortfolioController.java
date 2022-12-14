package stewart.jonathan.CryptoBytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stewart.jonathan.CryptoBytes.model.Crypto;
import stewart.jonathan.CryptoBytes.repository.CryptoRepository;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final CryptoRepository cryptoRepository;

    @Autowired
    public PortfolioController(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @GetMapping
    public List<Crypto> getAllCrypto() {
        return cryptoRepository.findAll();
    }
}
