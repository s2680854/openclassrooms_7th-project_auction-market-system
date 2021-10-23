package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Trade trade = new Trade("Trade Account", "Darty");

		// Save
		trade = tradeRepository.save(trade);
		assertEquals(trade.getAccount(), "Trade Account");

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		assertEquals(trade.getAccount(), "Trade Account Update");

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		assertTrue(listResult.size() > 0);

		//Find By ID
		Optional<Trade> optional = tradeRepository.findByType("Darty");
		Trade actual = new Trade();
		if (optional.isPresent()) {
			actual.setId(optional.get().getId());
			actual.setAccount(optional.get().getAccount());
			actual.setType(optional.get().getType());
			actual.setBuyQuantity(optional.get().getBuyQuantity());
			actual.setSellQuantity(optional.get().getSellQuantity());
			actual.setBuyPrice(optional.get().getBuyPrice());
			actual.setSellPrice(optional.get().getSellPrice());
			actual.setBenchmark(optional.get().getBenchmark());
			actual.setTradeDate(optional.get().getTradeDate());
			actual.setSecurity(optional.get().getSecurity());
			actual.setStatus(optional.get().getStatus());
			actual.setTrader(optional.get().getTrader());
			actual.setBook(optional.get().getBook());
			actual.setCreationName(optional.get().getCreationName());
			actual.setCreationDate(optional.get().getCreationDate());
			actual.setRevisionName(optional.get().getRevisionName());
			actual.setRevisionDate(optional.get().getRevisionDate());
			actual.setDealName(optional.get().getDealName());
			actual.setDealType(optional.get().getDealName());
			actual.setSourceListId(optional.get().getDealType());
			actual.setSide(optional.get().getSourceListId());
		}
		assertEquals(trade, actual);

		// Delete
		Long id = trade.getId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		assertFalse(tradeList.isPresent());
	}
}
