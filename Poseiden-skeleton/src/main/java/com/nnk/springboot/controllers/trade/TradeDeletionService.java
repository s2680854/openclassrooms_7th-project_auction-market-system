package com.nnk.springboot.controllers.trade;

import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeDeletionService {

    @Autowired
    private TradeRepository tradeRepository;

    public void deleteTradeById(Long id) {

        tradeRepository.deleteById(id);
    }

    public void deleteTrades() {

        tradeRepository.deleteAll();
    }
}
