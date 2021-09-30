package com.nnk.springboot.service.rule;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RuleReadService {

    @Autowired
    private RuleRepository ruleRepository;

    public Rule getRuleById(Long id) {

        return ruleRepository.getById(id);
    }

    public Collection<Rule> getRules() {

        return ruleRepository.findAll();
    }
}
