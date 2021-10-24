package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
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
    public void shouldGetRuleList() throws Exception {

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    public void shouldAddRule() throws Exception {

        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    public void shouldShowUpdateRuleForm() throws Exception {

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
    public void shouldValidateRule() throws Exception {

        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("Logistics");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleRepository.save(rule);

        rule.setDescription("Logistic");
        Long id = ruleRepository.findByName("Logistics").getId();
        rule.setId(id);

        mockMvc.perform(post("/ruleName/validate")
                        .param("id", rule.getId().toString())
                        .param("description", rule.getDescription())
                        .param("json", rule.getJson())
                        .param("name", rule.getName())
                        .param("sqlStr", rule.getSqlStr())
                        .param("sqlPart", rule.getSqlPart())
                        .param("template", rule.getTemplate()))
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void shouldUpdateRule() throws Exception {

        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("Trucks");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleRepository.save(rule);

        rule.setDescription("Truck");
        Long id = ruleRepository.findByName("Trucks").getId();
        rule.setId(id);

        mockMvc.perform(post("/ruleName/update/" + id)
                        .param("id", rule.getId().toString())
                        .param("description", rule.getDescription())
                        .param("json", rule.getJson())
                        .param("name", rule.getName())
                        .param("sqlStr", rule.getSqlStr())
                        .param("sqlPart", rule.getSqlPart())
                        .param("template", rule.getTemplate()))
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void shouldDeleteRule() throws Exception {

        Rule rule = new Rule();
        rule.setDescription("0");
        rule.setJson("0");
        rule.setName("Company");
        rule.setSqlStr("0");
        rule.setSqlPart("0");
        rule.setTemplate("0");
        ruleRepository.save(rule);
        Long id = ruleRepository.findByName("Company").getId();

        mockMvc.perform(delete("/ruleName/delete/" + id))
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void shouldDeleteRuleList() throws Exception {

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
