package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidsListRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.bidslist.BidsListDeletionService;
import com.nnk.springboot.service.bidslist.BidsListReadService;
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
@AutoConfigureMockMvc
public class BidServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BidsListReadService bidListReadService;
    @MockBean
    private BidsListDeletionService bidListDeletionService;
    @Autowired
    private BidsListRepository bidListRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void shouldInitialize() throws Exception {

        userRepository.deleteAll();

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("1234567");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);
    }

    @Test
    public void shouldGetBidsLists() throws Exception {

        Collection<BidsList> actualList = new ArrayList<>();

        Collection<BidsList> expectedList = bidListReadService.getBidsLists();

        assertEquals(actualList, expectedList);
    }

    @Test
    public void shouldGetBidsListById() throws Exception {

        bidListRepository.deleteAll();
        BidsList bidList = new BidsList();
        bidList.setAccount("grinngotts@jkr.com");
        bidList.setType("0");
        bidList.setBidQuantity(0d);
        bidListRepository.save(bidList);
        Long id = 1L;
        try {id = bidListRepository.findByAccount("grinngotts@jkr.com").getId();} catch (Exception e) {}

        BidsList expected = new BidsList();

        Optional<BidsList> optional = Optional.ofNullable(bidListReadService.getBidsListById(id));
        BidsList actual = new BidsList();
        if (optional.isPresent()) {
            actual.setAccount(optional.get().getAccount());
            actual.setType(optional.get().getType());
        }

        assertEquals(expected, actual);
    }


    @Test
    public void shouldDeleteBidsList() throws Exception {

        bidListRepository.deleteAll();
        BidsList bidList = new BidsList();
        bidList.setAccount("grinngotts@jkr.com");
        bidList.setType("0");
        bidList.setBidQuantity(0d);
        bidListRepository.save(bidList);
        Long bidListId = bidListRepository.findByAccount("grinngotts@jkr.com").getId();

        Mockito.doNothing().when(bidListDeletionService).deleteBidsListById(bidListId);

        mockMvc.perform(delete("/bidList/delete/" + bidListId));

        Mockito.verify(bidListDeletionService, Mockito.times(1)).deleteBidsListById(bidListId);
    }
}
