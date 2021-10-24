package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
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
    public void shouldGetBidsListList() throws Exception {

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));
    }

    @Test
    public void shouldAddBidsList() throws Exception {

        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public void shouldShowUpdateBidsListForm() throws Exception {

        bidListController.deleteAll();
        BidsList bid = new BidsList();
        bid.setAccount("david@test.com");
        bid.setType("0");
        bid.setBidQuantity(0d);
        bidsListRepository.save(bid);
        Long id = bidsListRepository.findByAccount("david@test.com").get().getId();

        mockMvc.perform(get("/bidList/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    public void shouldValidateBidsList() throws Exception {

        bidListController.deleteAll();
        BidsList bid = new BidsList();
        bid.setAccount("test3@test.com");
        bid.setType("0");
        bid.setBidQuantity(0d);
        bidsListRepository.save(bid);

        bid.setType("1");
        Long id = bidsListRepository.findByAccount("test3@test.com").get().getId();
        bid.setId(id);

        mockMvc.perform(post("/bidList/validate")
                        .param("id", bid.getId().toString())
                        .param("bidListname", bid.getAccount())
                        .param("password", bid.getType())
                        .param("fullame", String.valueOf(bid.getBidQuantity())))
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void shouldUpdateBidsList() throws Exception {

        bidListController.deleteAll();
        BidsList bid = new BidsList();
        bid.setAccount("test2@test.com");
        bid.setType("0");
        bid.setBidQuantity(0d);
        bidsListRepository.save(bid);

        bid.setType("1");
        Long id = bidsListRepository.findByAccount("test2@test.com").get().getId();
        bid.setId(id);

        mockMvc.perform(post("/bidList/update/" + id)
                        .param("id", bid.getId().toString())
                        .param("bidListname", bid.getAccount())
                        .param("password", bid.getType())
                        .param("fullame", String.valueOf(bid.getBidQuantity())))
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void shouldDeleteBidsList() throws Exception {

        bidListController.deleteAll();
        BidsList bid = new BidsList();
        bid.setAccount("test1@test.com");
        bid.setType("0");
        bid.setBidQuantity(0d);
        bidsListRepository.save(bid);
        Long id = bidsListRepository.findByAccount("test1@test.com").get().getId();

        mockMvc.perform(delete("/bidList/delete/" + id))
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void shouldDeleteBidsListList() throws Exception {

        bidListController.deleteAll();
        BidsList bid = new BidsList();
        bid.setAccount("david@test.com");
        bid.setType("0");
        bid.setBidQuantity(0d);
        bidsListRepository.save(bid);
        Long id = bidsListRepository.findByAccount("david@test.com").get().getId();

        mockMvc.perform(delete("/bidList/delete/" + id))
                .andExpect(view().name("redirect:/bidList/list"));
    }
}
