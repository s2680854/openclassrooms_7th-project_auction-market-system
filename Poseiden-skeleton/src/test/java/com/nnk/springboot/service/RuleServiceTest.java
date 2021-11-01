package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import com.nnk.springboot.service.rule.RuleCreationService;
import com.nnk.springboot.service.rule.RuleDeletionService;
import com.nnk.springboot.service.rule.RuleReadService;
import com.nnk.springboot.service.rule.RuleUpdateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class RuleServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RuleReadService ruleReadService;
    @Autowired
    private RuleUpdateService ruleUpdateService;
    @Autowired
    private RuleCreationService ruleCreationService;
    @MockBean
    private RuleDeletionService ruleDeletionService;
    @Autowired
    private RuleRepository ruleRepository;

    @Test
    public void shouldGetRules() throws Exception {

        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("Exchange");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleCreationService.createRule(rule);
        rule.setId(ruleRepository.findByName(rule.getName()).getId());
        Collection<Rule> actualList = new ArrayList<>();
        actualList.add(rule);

        Collection<Rule> expectedList = ruleReadService.getRules();

        assertEquals(actualList, expectedList);
    }

    @Test
    public void shouldGetRuleById() throws Exception {

        ruleRepository.deleteAll();
        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("Exchange");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleRepository.save(rule);
        Long id = 1L;
        try {id = ruleRepository.findByName("Exchange").getId();} catch (Exception e) {}
        rule.setId(id);

        Rule expected = new Rule();

        Optional<Rule> optional = Optional.ofNullable(ruleReadService.getRuleById(id));
        Rule actual = new Rule();
        if (optional.isPresent()) {
            actual.setDescription(optional.get().getDescription());
            actual.setJson(optional.get().getJson());
            actual.setName(optional.get().getName());
            actual.setSqlStr(optional.get().getSqlStr());
            actual.setSqlPart(optional.get().getSqlPart());
            actual.setTemplate(optional.get().getTemplate());
        }

        assertEquals(expected, actual);
    }


    @Test
    public void shouldDeleteRule() throws Exception {

        ruleRepository.deleteAll();
        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("International");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleRepository.save(rule);
        Long tradeId = ruleRepository.findByName("International").getId();

        Mockito.doNothing().when(ruleDeletionService).deleteRuleById(tradeId);

        mockMvc.perform(delete("/ruleName/delete/" + tradeId));

        Mockito.verify(ruleDeletionService, Mockito.times(1)).deleteRuleById(tradeId);
    }
}
