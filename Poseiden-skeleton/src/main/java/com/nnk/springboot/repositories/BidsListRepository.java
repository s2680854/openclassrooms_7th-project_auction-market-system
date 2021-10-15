package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidsListRepository extends JpaRepository<BidsList, Long> {

    @Query("SELECT b FROM BidsList b WHERE b.account = ?1")
    BidsList findByAccount(String account);
}
