package com.nnk.springboot.controllers.bidslist;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidsListUpdateService {

    @Autowired
    private BidsListRepository bidsListRepository;

    public BidsList updateBidsList(BidsList bidsList) {

        Optional<BidsList> optionalBidsList =
                bidsListRepository.findById(bidsList.getId());

        if (!optionalBidsList.isPresent()) {

            return new BidsList(bidsList.getAccount(), bidsList.getType(), bidsList.getBidQuantity());
        }
        return bidsListRepository.save(bidsList);
    }
}
