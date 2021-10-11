package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.controller.rule.RuleCreationService;
import com.nnk.springboot.controller.rule.RuleDeleteService;
import com.nnk.springboot.controller.rule.RuleReadService;
import com.nnk.springboot.controller.rule.RuleUpdateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class RuleNameController {

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.contains("@")) {
            model.addAttribute("username", username);
        } else {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            model.addAttribute("username", oAuth2User.getAttributes().get("email"));
        }
        logger.debug("[add bidList] authentication name: " + username);

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

        return "redirect:/ruleName/list";
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
    public String updateRuleName(@PathVariable("id") Long id, @Valid Rule rule,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "ruleName/add";
        }

        logger.debug("[post update] rule: " + rule);
        ruleUpdateService.updateRule(rule);

        return "redirect:/ruleName/list";
    }

    @DeleteMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Long id, Model model) {

        ruleDeleteService.deleteRuleById(id);

        return "redirect:/ruleName/list";
    }

    @DeleteMapping("/ruleName/delete")
    public String deleteAll() {

        ruleDeleteService.deleteRules();

        return "redirect:/ruleName/list";
    }
}
