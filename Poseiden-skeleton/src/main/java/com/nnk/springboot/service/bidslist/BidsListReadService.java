package com.nnk.springboot.service.bidslist;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BidsListReadService {

    @Autowired
    private BidsListRepository bidsListRepository;

    public BidsList getBidsList(Long id) {

        return bidsListRepository.getById(id);
    }

    public Collection<BidsList> getBidsLists() {

        return bidsListRepository.findAll();
    }
}
