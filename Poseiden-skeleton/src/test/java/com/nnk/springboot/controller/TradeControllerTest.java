package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
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
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"));
    }

    @Test
    public void shouldAddTrade() throws Exception {

        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    public void shouldShowUpdateTradeForm() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("david@test.com");
        trade.setType("Amazon");
        tradeRepository.save(trade);
        Long id = tradeRepository.findByType("Amazon").get().getId();

        mockMvc.perform(get("/trade/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }

    @Test
    public void shouldValidateUser() throws Exception {

        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Apple");
        tradeRepository.save(trade);

        trade.setDealType("someType");
        Long id = tradeRepository.findByType("Apple").get().getId();
        trade.setId(id);

        mockMvc.perform(post("/trade/validate")
                        .param("id", trade.getId().toString())
                        .param("account", trade.getAccount())
                        .param("type", trade.getType()))
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Fnac");
        tradeRepository.save(trade);

        trade.setDealType("someType");
        Long id = tradeRepository.findByType("Fnac").get().getId();
        trade.setId(id);

        mockMvc.perform(post("/trade/update/" + id)
                        .param("id", trade.getId().toString())
                        .param("account", trade.getAccount())
                        .param("type", trade.getType()))
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void shouldDeleteTrade() throws Exception {

        Trade trade = new Trade();
        trade.setAccount("grinngotts@jkr.com");
        trade.setType("Boulanger");
        tradeRepository.save(trade);
        Long id = tradeRepository.findByType("Boulanger").get().getId();

        mockMvc.perform(delete("/trade/delete/" + id))
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void shouldDeleteTradeList() throws Exception {

        tradeRepository.deleteAll();
        Trade trade = new Trade();
        trade.setAccount("david@test.com");
        trade.setType("Free");
        tradeRepository.save(trade);
        Long id = tradeRepository.findByType("Free").get().getId();

        mockMvc.perform(delete("/trade/delete/" + id))
                .andExpect(view().name("redirect:/trade/list"));
    }
}
