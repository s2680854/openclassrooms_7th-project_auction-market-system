package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.service.rule.RuleCreationService;
import com.nnk.springboot.service.rule.RuleDeleteService;
import com.nnk.springboot.service.rule.RuleReadService;
import com.nnk.springboot.service.rule.RuleUpdateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class RuleController {

    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private RuleCreationService ruleCreationService;
    @Autowired
    private RuleReadService ruleReadService;
    @Autowired
    private RuleUpdateService ruleUpdateService;
    @Autowired
    private RuleDeleteService ruleDeleteService;

    @GetMapping("/ruleName/list")
    public String home(Model model) {

        Collection<Rule> rules = ruleReadService.getRules();
        model.addAttribute("rules", rules);
        logger.debug("[home] rules list: " + rules);

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {

        Rule rule = new Rule();
        model.addAttribute(rule);
        logger.debug("[add] rule: " + rule);

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid Rule rule, BindingResult result, Model model) {

        // TODO: check if we must return to add
        if (result.hasErrors()) {
            return "bidList/add";
        }

        model.addAttribute(rule);
        logger.debug("[validate] rule: " + rule);
        ruleCreationService.createRule(rule);

        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        // TODO: check if I have to update
        Rule rule = ruleReadService.getRuleById(id);
        logger.debug("[get update] rule: " + rule);
        model.addAttribute("rule", rule);

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid Rule rule,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/ruleName/list";
    }
}
