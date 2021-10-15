package com.nnk.springboot.service;

import com.nnk.springboot.controller.BidListController;
import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
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
public class BidControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BidsListRepository bidsListRepository;
    @Autowired
    private BidListController bidListController;

    @Before("")
    public void setup() {

        bidListController = new BidListController();
        mockMvc = MockMvcBuilders.standaloneSetup(bidListController).build();
    }

    @Test
    public void shouldShowUpdateTradeForm() throws Exception {

        bidListController.deleteAll();
        BidsList bid = new BidsList();
        bid.setAccount("david@test.com");
        bid.setType("0");
        bid.setBidQuantity(0d);
        bidsListRepository.save(bid);
        Long id = bidsListRepository.findByAccount("david@test.com").getId();

        mockMvc.perform(get("/bidList/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    public void shouldDeleteTrade() throws Exception {

        bidListController.deleteAll();
        BidsList bid = new BidsList();
        bid.setAccount("david@test.com");
        bid.setType("0");
        bid.setBidQuantity(0d);
        bidsListRepository.save(bid);
        Long id = bidsListRepository.findByAccount("david@test.com").getId();

        mockMvc.perform(delete("/bidList/delete/" + id))
                .andExpect(view().name("redirect:/bidList/list"));
    }
}
