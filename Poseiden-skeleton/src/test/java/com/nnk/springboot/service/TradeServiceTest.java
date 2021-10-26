package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.trade.TradeDeletionService;
import com.nnk.springboot.service.trade.TradeReadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class TradeServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TradeReadService tradeReadService;
    @MockBean
    private TradeDeletionService tradeDeletionService;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void shouldInitialize() throws Exception {

        userRepository.deleteAll();

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);
    }

    @Test
    public void shouldGetTrades() throws Exception {

        Collection<Trade> actualList = new ArrayList<>();

        Collection<Trade> expectedList = tradeReadService.getTrades();

        assertEquals(actualList, expectedList);
    }

    @Test
    public void shouldGetTradeById() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Amazon");
        tradeRepository.save(trade);
        Long id = 1L;
        try {id = tradeRepository.findByType("Amazon").get().getId();} catch (Exception e) {}

        Trade expected = new Trade();

        Optional<Trade> optional = Optional.ofNullable(tradeReadService.getTradeById(id));
        Trade actual = new Trade();
        if (optional.isPresent()) {
            actual.setAccount(optional.get().getAccount());
            actual.setType(optional.get().getType());
        }

        assertEquals(expected, actual);
    }


    @Test
    public void shouldDeleteTrade() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Cdiscount");
        tradeRepository.save(trade);
        Long tradeId = tradeRepository.findByType("Cdiscount").get().getId();

        Mockito.doNothing().when(tradeDeletionService).deleteTradeById(tradeId);

        mockMvc.perform(delete("/trade/delete/" + tradeId));

        Mockito.verify(tradeDeletionService, Mockito.times(1)).deleteTradeById(tradeId);
    }
}
