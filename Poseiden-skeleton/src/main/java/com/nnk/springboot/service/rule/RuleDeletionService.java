package com.nnk.springboot.service.rule;

import com.nnk.springboot.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleDeletionService {

    @Autowired
    private RuleRepository ruleRepository;

    public void deleteRuleById(Long id) {

        ruleRepository.deleteById(id);
    }

    public void deleteRules() {

        ruleRepository.deleteAll();
    }
}
