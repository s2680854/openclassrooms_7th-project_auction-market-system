package com.nnk.springboot.service.rule;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuleUpdateService {

    @Autowired
    private RuleRepository ruleRepository;

    public Rule updateRule(Rule rule) {

        Optional<Rule> optionalRule =
                ruleRepository.findById(rule.getId());

        if (!optionalRule.isPresent()) {

            return new Rule(rule.getName(), rule.getDescription(), rule.getJson(),
                    rule.getTemplate(), rule.getSqlStr(), rule.getSqlPart());
        }
        return ruleRepository.save(rule);
    }
}
