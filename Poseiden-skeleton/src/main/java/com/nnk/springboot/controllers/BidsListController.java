package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.service.bidslist.BidsListCreationService;
import com.nnk.springboot.service.bidslist.BidsListDeletionService;
import com.nnk.springboot.service.bidslist.BidsListReadService;
import com.nnk.springboot.service.bidslist.BidsListUpdateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class BidsListController {

    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private BidsListCreationService bidsListCreationService;
    @Autowired
    private BidsListReadService bidsListReadService;
    @Autowired
    private BidsListUpdateService bidsListUpdateService;
    @Autowired
    private BidsListDeletionService bidsListDeletionService;

    @GetMapping("/bidList/list")
    public String home(Model model) {

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticationName = authentication.getName();
        logger.debug("[home] authentication name: " + authenticationName);

        Collection<BidsList> bidsList = bidsListReadService.getBidsListByEmail(authentication.getName());*/
        Collection<BidsList> bidsList = bidsListReadService.getBidsLists();
        model.addAttribute("bidsList", bidsList);
        logger.debug("[home] bids list: " + bidsList);

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticationName = authentication.getName();
        logger.debug("[home] authentication name: " + authenticationName);

        BidsList bid = new BidsList();
        bid.setAccount(authenticationName);
        model.addAttribute(bid);
        logger.debug("[add] bid: " + bid);

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidsList bid, BindingResult result, Model model) {

        // TODO: check if we must return to add
        if (result.hasErrors()) {
            return "bidList/add";
        }

        model.addAttribute(bid);
        logger.debug("[validate] bid: " + bid);
        bidsListCreationService.createBidsList(bid);

        return "redirect:/bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        // TODO: check if I have to update
        BidsList bid = bidsListReadService.getBidsListById(id);
        logger.debug("[get update] bid: " + bid);
        model.addAttribute("bid", bid);

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid BidsList bidsList,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "bidList/add";
        }

        logger.debug("[post update] bid: " + bidsList);
        bidsListUpdateService.updateBidsList(bidsList);

        return "redirect:/bidList/list";
    }

    @DeleteMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {

        bidsListDeletionService.deleteBidsListById(id);

        return "redirect:/bidList/list";
    }
}
