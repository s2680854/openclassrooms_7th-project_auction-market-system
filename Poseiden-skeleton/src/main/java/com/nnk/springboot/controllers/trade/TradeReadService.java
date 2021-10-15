package com.nnk.springboot.controllers.trade;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TradeReadService {

    @Autowired
    private TradeRepository tradeRepository;

    public Trade getTradeById(Long id) {

        return tradeRepository.getById(id);
    }

    public Collection<Trade> getTrades() {

        return tradeRepository.findAll();
    }
}
