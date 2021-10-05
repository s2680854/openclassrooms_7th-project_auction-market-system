package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidsList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.bidslist.BidsListCreationService;
import com.nnk.springboot.service.bidslist.BidsListDeletionService;
import com.nnk.springboot.service.bidslist.BidsListReadService;
import com.nnk.springboot.service.bidslist.BidsListUpdateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

@Controller
public class BidListController {

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.contains("@")) {
            model.addAttribute("username", username);
        } else {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            model.addAttribute("username", oAuth2User.getAttributes().get("huemail"));
        }
        logger.debug("[add bidList] authentication name: " + username);

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
        String username = authentication.getName();
        if (!username.contains("@")) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            username = oAuth2User.getAttributes().get("email").toString();
        }
        model.addAttribute("username", username);
        logger.debug("[add bidList] username: " + username);

        BidsList bid = new BidsList();
        bid.setAccount(username);
        model.addAttribute(bid);
        logger.debug("[add] bid: " + bid);

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidsList bid, BindingResult result, Model model) {


        logger.debug("[validate bid] account: " + bid.getAccount());
        model.addAttribute(bid);

        if (result.hasErrors()) {
            return "bidList/add";
        }

        logger.debug("[validate] bid: " + bid);
        bidsListCreationService.createBidsList(bid);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

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
    public String deleteBid(@PathVariable("id") Long id) {

        bidsListDeletionService.deleteBidsListById(id);

        return "redirect:/bidList/list";
    }

    @DeleteMapping("/bidList/delete")
    public String deleteBid() {

        bidsListDeletionService.deleteBidsLists();

        return "redirect:/bidList/list";
    }
}
