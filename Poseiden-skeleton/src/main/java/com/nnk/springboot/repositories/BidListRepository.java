package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidsList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BidListRepository extends JpaRepository<BidsList, Integer> {

}
