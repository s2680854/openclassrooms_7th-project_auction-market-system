package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidsListRepository extends JpaRepository<BidsList, Long> {

    Optional<BidsList> findByAccount(String account);
}
