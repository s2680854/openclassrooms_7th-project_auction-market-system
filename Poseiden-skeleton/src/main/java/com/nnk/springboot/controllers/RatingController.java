package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.rating.RatingCreationgService;
import com.nnk.springboot.service.rating.RatingDeletionService;
import com.nnk.springboot.service.rating.RatingReadService;
import com.nnk.springboot.service.rating.RatingUpdateService;
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
public class RatingController {

    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private RatingCreationgService ratingCreationgService;
    @Autowired
    private RatingReadService ratingReadService;
    @Autowired
    private RatingUpdateService ratingUpdateService;
    @Autowired
    private RatingDeletionService ratingDeletionService;

    @GetMapping("/rating/list")
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

        Collection<Rating> ratings = ratingReadService.getRatings();
        model.addAttribute("ratings", ratings);
        logger.debug("[home] rating list: " + ratings);

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {

        Rating rating = new Rating();
        model.addAttribute(rating);
        logger.debug("[add] rating: " + rating);

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        // TODO: check if we must return to add
        if (result.hasErrors()) {
            return "rating/add";
        }

        model.addAttribute(rating);
        logger.debug("[validate] rating: " + rating);
        ratingCreationgService.createRating(rating);

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        // TODO: check if I have to update
        Rating rating = ratingReadService.getRatingById(id);
        logger.debug("[get update] rating: " + rating);
        model.addAttribute("rating", rating);

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Long id, @Valid Rating rating,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "rating/add";
        }

        logger.debug("[post update] rating: " + rating);
        ratingUpdateService.updateRating(rating);

        return "redirect:/rating/list";
    }

    @DeleteMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Long id, Model model) {

        ratingDeletionService.deleteRatingById(id);

        return "redirect:/rating/list";
    }

    @DeleteMapping("/rating/delete")
    public String deleteAll() {

        ratingDeletionService.deleteRatings();

        return "redirect:/rating/list";
    }
}
