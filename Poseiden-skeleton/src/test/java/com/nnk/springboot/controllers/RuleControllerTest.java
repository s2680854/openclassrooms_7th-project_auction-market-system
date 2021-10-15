package com.nnk.springboot.controllers;

import com.nnk.springboot.controller.RuleNameController;
import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private RuleNameController ruleNameController;

    @Before("")
    public void setup() {

        ruleNameController = new RuleNameController();
        mockMvc = MockMvcBuilders.standaloneSetup(ruleNameController).build();
    }

    @Test
    public void shouldShowUpdateTradeForm() throws Exception {

        ruleNameController.deleteAll();
        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("0");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleRepository.save(rule);
        Long id = ruleRepository.findByName("0").getId();

        mockMvc.perform(get("/ruleName/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    public void shouldDeleteTrade() throws Exception {

        ruleNameController.deleteAll();
        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("0");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleRepository.save(rule);
        Long id = ruleRepository.findByName("0").getId();

        mockMvc.perform(delete("/ruleName/delete/" + id))
                .andExpect(view().name("redirect:/ruleName/list"));
    }
}
