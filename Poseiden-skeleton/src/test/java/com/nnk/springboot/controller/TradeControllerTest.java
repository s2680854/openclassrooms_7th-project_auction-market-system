package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private TradeController tradeController;

    @Before("")
    public void setup() {

        tradeController = new TradeController();
        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
    }

    @Test
    public void shouldGetTradeList() throws Exception {

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().is(401))
                .andExpect(view().name("trade/list"));
    }

    @Test
    public void shouldAddTrade() throws Exception {

        mockMvc.perform(get("/trade/add"))
                .andExpect(status().is(401))
                .andExpect(view().name("trade/add"));
    }

    @Test
    public void shouldShowUpdateTradeForm() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("david@test.com");
        trade.setType("Amazon");
        tradeRepository.save(trade);
        Long id = tradeRepository.findByType("Amazon").getId();

        mockMvc.perform(get("/trade/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }

    @Test
    public void shouldDeleteTrade() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("david@test.com");
        trade.setType("Free");
        tradeRepository.save(trade);
        Long id = tradeRepository.findByType("Free").getId();

        mockMvc.perform(delete("/trade/delete/" + id))
                .andExpect(status().is(401))
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void shouldDeleteTradeList() throws Exception {

        mockMvc.perform(delete("/trade/delete"))
                .andExpect(status().is(401))
                .andExpect(view().name("redirect:/trade/list"));
    }
}
