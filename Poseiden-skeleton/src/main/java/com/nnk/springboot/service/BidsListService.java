package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Service
public class BidsListService {

    @Autowired
    private BidsListRepository bidsListRepository;

    public Collection<BidsList> getBidsLists() {

        return bidsListRepository.findAll();
    }

    public BidsList creatBidsList(BidsList bidsList) {

        return bidsListRepository.save(bidsList);
    }

    public BidsList updateBidsList(BidsList bidsList) {

        Optional<BidsList> optionalBidsList =
                bidsListRepository.findById(bidsList.getBidListId());

        if (!optionalBidsList.isPresent()) {

            return new BidsList();
        }
        return bidsListRepository.save(bidsList);
    }

    public void deleteBidById(Long id) {

        bidsListRepository.deleteById(id);
    }
}
