package com.nnk.springboot.service.bidslist;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BidsListReadService {

    @Autowired
    private BidsListRepository bidsListRepository;

    public Optional<BidsList> getBidsListById(Long id) {

        return bidsListRepository.findById(id);
    }

    public Collection<BidsList> getBidsLists() {

        return bidsListRepository.findAll();
    }
}
