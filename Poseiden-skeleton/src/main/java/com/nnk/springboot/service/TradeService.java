package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public Trade getTrade(Long id) {

        return tradeRepository.getById(id);
    }

    public Collection<Trade> getTrades() {

        return tradeRepository.findAll();
    }

    public Trade createTrade(Trade trade) {

        return tradeRepository.save(trade);
    }

    public Collection<Trade> createRules(Collection<Trade> trades) {

        return tradeRepository.saveAll(trades);
    }

    public Trade updateTrade(Trade trade) {

        Optional<Trade> optionalTrade =
                tradeRepository.findById(trade.getId());

        if (!optionalTrade.isPresent()) {

            return new Trade(trade.getAccount(), trade.getType());
        }
        return tradeRepository.save(trade);
    }

    public void deleteTradeById(Long id) {

        tradeRepository.deleteById(id);
    }

    public void deleteTrades() {

        tradeRepository.deleteAll();
    }
}
