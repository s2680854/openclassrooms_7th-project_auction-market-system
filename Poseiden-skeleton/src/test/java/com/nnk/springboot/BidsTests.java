package com.nnk.springboot;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.repositories.BidsListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidsTests {

	@Autowired
	private BidsListRepository bidsListRepository;

	@Test
	public void bidListTest() {
		BidsList bid = new BidsList("david@test.com", "Type Test", 10d);

		// Save
		bid = bidsListRepository.save(bid);
		assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidsListRepository.save(bid);
		assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidsList> listResult = bidsListRepository.findAll();
		assertTrue(listResult.size() > 0);

		//Find By ID
		Optional<BidsList> optional = bidsListRepository.findByAccount("david@test.com");
		BidsList actual = new BidsList();
		if (optional.isPresent()) {
			actual.setId(optional.get().getId());
			actual.setAccount(optional.get().getAccount());
			actual.setType(optional.get().getType());
			actual.setBidQuantity(optional.get().getBidQuantity());
			actual.setAskQuantity(optional.get().getAskQuantity());
			actual.setBid(optional.get().getBid());
			actual.setAsk(optional.get().getAsk());
			actual.setBenchmark(optional.get().getBenchmark());
			actual.setBidsListDate(optional.get().getBidsListDate());
			actual.setCommentary(optional.get().getCommentary());
			actual.setSecurity(optional.get().getSecurity());
			actual.setStatus(optional.get().getStatus());
			actual.setTrader(optional.get().getTrader());
			actual.setBook(optional.get().getBook());
			actual.setCreationName(optional.get().getCreationName());
			actual.setCreationDate(optional.get().getCreationDate());
			actual.setRevisionName(optional.get().getRevisionName());
			actual.setRevisionDate(optional.get().getRevisionDate());
			actual.setDealName(optional.get().getDealName());
			actual.setDealType(optional.get().getDealType());
			actual.setSourceListId(optional.get().getSourceListId());
			actual.setSide(optional.get().getSide());
		}
		assertEquals(bid, actual);

		// Delete
		Long id = bid.getId();
		bidsListRepository.delete(bid);
		Optional<BidsList> bidList = bidsListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
}
