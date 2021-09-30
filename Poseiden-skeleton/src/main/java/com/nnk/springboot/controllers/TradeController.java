package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.bidslist.BidsListCreationService;
import com.nnk.springboot.service.bidslist.BidsListDeletionService;
import com.nnk.springboot.service.bidslist.BidsListReadService;
import com.nnk.springboot.service.bidslist.BidsListUpdateService;
import com.nnk.springboot.service.trade.TradeCreationService;
import com.nnk.springboot.service.trade.TradeDeletionService;
import com.nnk.springboot.service.trade.TradeReadService;
import com.nnk.springboot.service.trade.TradeUpdateService;
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
    public String home(Model model)
    {
        Collection<Trade> trades = tradeReadService.getTrades();
        model.addAttribute("trades", trades);
        logger.debug("[home] trades list: " + trades);

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {

        Trade trade = new Trade();
        logger.debug("[add] trade: " + trade);
        
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        return "redirect:/trade/list";
    }
}
