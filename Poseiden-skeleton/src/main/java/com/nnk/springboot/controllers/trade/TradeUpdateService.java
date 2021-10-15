package com.nnk.springboot.controllers.trade;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeUpdateService {

    @Autowired
    private TradeRepository tradeRepository;

    public Trade updateTrade(Trade trade) {

        Optional<Trade> optionalTrade =
                tradeRepository.findById(trade.getId());

        if (!optionalTrade.isPresent()) {

            return new Trade(trade.getAccount(), trade.getType());
        }
        return tradeRepository.save(trade);
    }
}
