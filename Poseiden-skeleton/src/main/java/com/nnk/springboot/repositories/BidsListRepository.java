package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;


public interface BidsListRepository extends JpaRepository<BidsList, Long> {

    @Query("SELECT b FROM bids_list b WHERE b.account = ?1")
    Collection<BidsList> findByEmail(String email);
}
