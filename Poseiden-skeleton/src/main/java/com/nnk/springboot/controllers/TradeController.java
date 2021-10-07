package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.trade.TradeCreationService;
import com.nnk.springboot.service.trade.TradeDeletionService;
import com.nnk.springboot.service.trade.TradeReadService;
import com.nnk.springboot.service.trade.TradeUpdateService;
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
public class TradeController {

    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private TradeCreationService tradeCreationService;
    @Autowired
    private TradeReadService tradeReadService;
    @Autowired
    private TradeUpdateService tradeUpdateService;
    @Autowired
    private TradeDeletionService tradeDeletionService;

    @GetMapping("/trade/list")
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

        Collection<Trade> trades = tradeReadService.getTrades();
        model.addAttribute("trades", trades);
        logger.debug("[home] trades list: " + trades);

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Model model) {

        Trade trade = new Trade();
        model.addAttribute("trade", trade);
        logger.debug("[add] trade: " + trade);

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        // TODO: check if we must return to add
        if (result.hasErrors()) {
            return "trade/add";
        }

        model.addAttribute(trade);
        logger.debug("[validate] trade: " + trade);
        tradeCreationService.createTrade(trade);

        return "redirect:/trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        // TODO: check if I have to update
        Trade trade = tradeReadService.getTradeById(id);
        logger.debug("[get update] trade: " + trade);
        model.addAttribute("trade", trade);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Long id, @Valid Trade trade,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "trade/add";
        }

        logger.debug("[post update] trade: " + trade);
        tradeUpdateService.updateTrade(trade);

        return "redirect:/trade/list";
    }

    @DeleteMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Long id, Model model) {

        tradeDeletionService.deleteTradeById(id);

        return "redirect:/trade/list";
    }

    @DeleteMapping("/trade/delete")
    public String deleteAll() {

        tradeDeletionService.deleteTrades();

        return "redirect:/trade/list";
    }
}
