package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.rating.RatingCreationgService;
import com.nnk.springboot.service.rating.RatingDeletionService;
import com.nnk.springboot.service.rating.RatingReadService;
import com.nnk.springboot.service.rating.RatingUpdateService;
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

        return "rating/add";
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
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        return "redirect:/rating/list";
    }
}
