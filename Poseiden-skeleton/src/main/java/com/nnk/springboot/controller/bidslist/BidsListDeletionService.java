package com.nnk.springboot.controller.bidslist;

import com.nnk.springboot.repositories.BidsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidsListDeletionService {

    @Autowired
    private BidsListRepository bidsListRepository;

    public void deleteBidsListById(Long id) {

        bidsListRepository.deleteById(id);
    }

    public void deleteBidsLists() {

        bidsListRepository.deleteAll();
    }
}
