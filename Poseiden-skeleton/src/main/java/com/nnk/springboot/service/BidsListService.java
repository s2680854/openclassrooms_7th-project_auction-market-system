package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BidsListService {

    @Autowired
    private BidsListRepository bidsListRepository;

    public BidsList getBidsList(Long id) {

        return bidsListRepository.getById(id);
    }

    public Collection<BidsList> getBidsLists() {

        return bidsListRepository.findAll();
    }

    public BidsList creatBidsList(BidsList bidsList) {

        return bidsListRepository.save(bidsList);
    }

    public Collection<BidsList> creatBidsLists(Collection<BidsList> bidsLists) {

        return bidsListRepository.saveAll(bidsLists);
    }

    public BidsList updateBidsList(BidsList bidsList) {

        Optional<BidsList> optionalBidsList =
                bidsListRepository.findById(bidsList.getId());

        if (!optionalBidsList.isPresent()) {

            return new BidsList(bidsList.getAccount(), bidsList.getType(), bidsList.getBidQuantity());
        }
        return bidsListRepository.save(bidsList);
    }

    public void deleteBidsListById(Long id) {

        bidsListRepository.deleteById(id);
    }

    public void deleteBidsLists() {

        bidsListRepository.deleteAll();
    }
}
