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
            model.addAttribute("username", oAuth2User.getAttributes().get("email"));
        }
        logger.debug("[adding bidList] authentication name: " + username);

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticationName = authentication.getName();
        logger.debug("[home] authentication name: " + authenticationName);

        Collection<BidsList> bidsList = bidsListReadService.getBidsListByEmail(authentication.getName());*/
        Collection<BidsList> bidsList = bidsListReadService.getBidsLists();
        model.addAttribute("bidsList", bidsList);
        logger.debug("[reading bids] bids: " + bidsList);

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {

        BidsList bid = new BidsList();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (!username.contains("@")) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            username = oAuth2User.getAttributes().get("email").toString();
        }
        logger.debug("[adding bid] username: " + username);
        model.addAttribute("username", username);

        bid.setAccount(username);
        model.addAttribute(bid);

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidsList bid, BindingResult result, Model model) {


        logger.debug("[validating bid] account: " + bid.getAccount());
        model.addAttribute(bid);

        if (result.hasErrors()) {
            return "bidList/add";
        }

        logger.debug("[validating bid] bid: " + bid);
        bidsListCreationService.createBidsList(bid);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        logger.debug("[updating bid form] show update form id: " + id);
        BidsList bid = bidsListReadService.getBidsListById(id);
        model.addAttribute("bid", bid);

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid BidsList bidsList,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "bidList/add";
        }

        logger.debug("[updating bid]  id: " + id);
        bidsListUpdateService.updateBidsList(bidsList);

        return "redirect:/bidList/list";
    }

    @RequestMapping(value="/bidList/delete/{id}", method = RequestMethod.DELETE)
    public String deleteBid(@PathVariable Long id) {

        bidsListDeletionService.deleteBidsListById(id);
        logger.debug("[deleting bid] id: " + id);

        return "redirect:/bidList/list";
    }

    @DeleteMapping("/bidList/delete")
    public String deleteAll() {

        bidsListDeletionService.deleteBidsLists();
        logger.debug("[deleting bid] bids: all");

        return "redirect:/bidList/list";
    }
}
