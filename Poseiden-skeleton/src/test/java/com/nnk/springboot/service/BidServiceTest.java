package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidsListRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.bidslist.BidsListCreationService;
import com.nnk.springboot.service.bidslist.BidsListDeletionService;
import com.nnk.springboot.service.bidslist.BidsListReadService;
import com.nnk.springboot.service.bidslist.BidsListUpdateService;
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
public class BidServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BidsListCreationService bidsListCreationService;
    @Autowired
    private BidsListReadService bidListReadService;
    @Autowired
    private BidsListUpdateService bidsListUpdateService;
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
        user.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);
    }

    @Test
    public void shouldCreateBidsList() throws Exception {

        bidListRepository.deleteAll();
        BidsList bidList = new BidsList();
        bidList.setAccount("grinngotts@jkr.com");
        bidList.setType("0");
        bidList.setBidQuantity(0d);
        bidsListCreationService.createBidsList(bidList);
        bidList.setId(bidListRepository.findByAccount(bidList.getAccount()).get().getId());

        BidsList actual = bidListReadService.getBidsListById(bidList.getId()).get();

        assertEquals(bidList, actual);
    }

    @Test
    public void shouldGetBidsLists() throws Exception {

        bidListRepository.deleteAll();
        BidsList bidList = new BidsList();
        bidList.setAccount("grinngotts@jkr.com");
        bidList.setType("0");
        bidList.setBidQuantity(0d);
        bidsListCreationService.createBidsList(bidList);
        Collection<BidsList> actualList = new ArrayList<>();
        actualList.add(bidList);

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
        Long id = bidListRepository.findByAccount("grinngotts@jkr.com").get().getId();

        Optional<BidsList> optional = bidListReadService.getBidsListById(id);
        BidsList actual = new BidsList();
        if (optional.isPresent()) {

            actual.setAccount(optional.get().getAccount());
            actual.setType(optional.get().getType());
            actual.setBidQuantity(optional.get().getBidQuantity());
        }

        assertEquals(bidList, actual);
    }

    @Test
    public void shouldUpdateBidsList() throws Exception {

        bidListRepository.deleteAll();
        BidsList bidList = new BidsList();
        bidList.setAccount("grinngotts@jkr.com");
        bidList.setType("0");
        bidList.setBidQuantity(0d);
        bidsListCreationService.createBidsList(bidList);
        bidList.setId(bidListRepository.findByAccount(bidList.getAccount()).get().getId());
        bidList.setType("Boutique");
        bidsListUpdateService.updateBidsList(bidList);

        BidsList actual = bidListReadService.getBidsListById(bidList.getId()).get();

        assertEquals(bidList, actual);
    }

    @Test
    public void shouldDeleteBidsList() throws Exception {

        bidListRepository.deleteAll();
        BidsList bidList = new BidsList();
        bidList.setAccount("grinngotts@jkr.com");
        bidList.setType("0");
        bidList.setBidQuantity(0d);
        bidListRepository.save(bidList);
        Long bidListId = bidListRepository.findByAccount("grinngotts@jkr.com").get().getId();

        Mockito.doNothing().when(bidListDeletionService).deleteBidsListById(bidListId);

        mockMvc.perform(delete("/bidList/delete/" + bidListId));

        Mockito.verify(bidListDeletionService, Mockito.times(1)).deleteBidsListById(bidListId);
    }
}
