package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.trade.TradeCreationService;
import com.nnk.springboot.service.trade.TradeDeletionService;
import com.nnk.springboot.service.trade.TradeReadService;
import com.nnk.springboot.service.trade.TradeUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters=false)
public class TradeServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TradeCreationService tradeCreationService;
    @Autowired
    private TradeUpdateService tradeUpdateService;
    @Autowired
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
    public void shouldCreateTrade() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Boulanger Electrom??nager");
        trade.setStatus("ACTIVE");
        trade = tradeCreationService.createTrade(trade);
        trade.setId(tradeRepository.findByType(trade.getType()).get().getId());

        Trade actual = tradeReadService.getTradeById(trade.getId());

        assertEquals(trade, actual);
    }

    @Test
    public void shouldGetTrades() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Boulanger Electrom??nager");
        trade.setStatus("ACTIVE");
        trade = tradeCreationService.createTrade(trade);
        trade.setId(tradeRepository.findByType(trade.getType()).get().getId());
        Collection<Trade> actualList = new ArrayList<>();
        actualList.add(trade);

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

        Optional<Trade> optional = Optional.ofNullable(tradeReadService.getTradeById(id));
        Trade actual = new Trade();
        if (optional.isPresent()) {
            actual.setAccount(optional.get().getAccount());
            actual.setType(optional.get().getType());
        }

        assertEquals(trade, actual);
    }

    @Test
    public void shouldUpdateTrade() throws Exception {

        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Darty Electrom??nager");
        tradeCreationService.createTrade(trade);
        trade.setId(tradeRepository.findByType(trade.getType()).get().getId());

        trade.setDealName("Boutique");
        Trade expected = tradeUpdateService.updateTrade(trade);
        Trade actual = tradeReadService.getTradeById(trade.getId());

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
