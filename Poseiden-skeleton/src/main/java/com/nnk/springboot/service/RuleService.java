package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public Rule getRule(Long id) {

        return ruleRepository.getById(id);
    }

    public Collection<Rule> getRules() {

        return ruleRepository.findAll();
    }

    public Rule createRule(Rule rule) {

        return ruleRepository.save(rule);
    }

    public Collection<Rule> createRules(Collection<Rule> rules) {

        return ruleRepository.saveAll(rules);
    }

    public Rule updateRule(Rule rule) {

        Optional<Rule> optionalRule =
                ruleRepository.findById(rule.getId());

        if (!optionalRule.isPresent()) {

            return new Rule(rule.getName(), rule.getDescription(), rule.getJson(),
                    rule.getTemplate(), rule.getSqlStr(), rule.getSqlPart());
        }
        return ruleRepository.save(rule);
    }

    public void deleteRuleById(Long id) {

        ruleRepository.deleteById(id);
    }

    public void deleteRules() {

        ruleRepository.deleteAll();
    }
}
