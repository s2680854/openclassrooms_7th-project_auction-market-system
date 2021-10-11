package com.nnk.springboot.controller.rule;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RuleCreationService {

    @Autowired
    private RuleRepository ruleRepository;

    public Rule createRule(Rule rule) {

        return ruleRepository.save(rule);
    }

    public Collection<Rule> createRules(Collection<Rule> rules) {

        return ruleRepository.saveAll(rules);
    }
}
