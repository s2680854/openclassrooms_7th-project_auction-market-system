package com.nnk.springboot.controller.trade;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TradeCreationService {

    @Autowired
    private TradeRepository tradeRepository;

    public Trade createTrade(Trade trade) {

        return tradeRepository.save(trade);
    }

    public Collection<Trade> createTrades(Collection<Trade> trades) {

        return tradeRepository.saveAll(trades);
    }
}
