package com.nnk.springboot.controllers.bidslist;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BidsListCreationService {

    @Autowired
    private BidsListRepository bidsListRepository;

    public BidsList createBidsList(BidsList bidsList) {

        return bidsListRepository.save(bidsList);
    }

    public Collection<BidsList> createBidsLists(Collection<BidsList> bidsLists) {

        return bidsListRepository.saveAll(bidsLists);
    }
}
