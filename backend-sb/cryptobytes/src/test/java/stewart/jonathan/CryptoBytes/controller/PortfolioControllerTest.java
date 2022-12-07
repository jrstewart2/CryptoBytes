package stewart.jonathan.CryptoBytes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import stewart.jonathan.CryptoBytes.model.Portfolio;
import stewart.jonathan.CryptoBytes.repository.PortfolioRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@WebMvcTest(PortfolioController.class)
public class PortfolioControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    PortfolioRepository portfolioRepository;

    Portfolio COIN_1 = new Portfolio("BTC", "Bitcoin", 0.173);
    Portfolio COIN_2 = new Portfolio("ETH", "Ethereum", 0.65);
    Portfolio COIN_3 = new Portfolio("LRC", "Loopring", 65.1);


    @Test
    public void getPortfolioTest() throws Exception {
        List<Portfolio> portfolio = new ArrayList<>(asList(COIN_1, COIN_2, COIN_3));

        Mockito.when(portfolioRepository.findAll()).thenReturn(portfolio);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/portfolio")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[2].name", is("Loopring")));

    }
}
