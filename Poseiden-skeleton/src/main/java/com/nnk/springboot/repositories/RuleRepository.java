package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    @Query("SELECT r FROM Rule r WHERE r.name = ?1")
    Rule findByName(String name);
}
