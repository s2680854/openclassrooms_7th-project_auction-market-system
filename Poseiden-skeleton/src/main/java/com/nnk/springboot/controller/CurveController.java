package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.controllers.curvepoint.CurvePointCreationService;
import com.nnk.springboot.controllers.curvepoint.CurvePointDeletionService;
import com.nnk.springboot.controllers.curvepoint.CurvePointReadService;
import com.nnk.springboot.controllers.curvepoint.CurvePointUpdateService;
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
public class CurveController {

    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private CurvePointCreationService curvePointCreationService;
    @Autowired
    private CurvePointReadService curvePointReadService;
    @Autowired
    private CurvePointUpdateService curvePointUpdateService;
    @Autowired
    private CurvePointDeletionService curvePointDeletionService;

    @GetMapping("/curvePoint/list")
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.contains("@")) {
            model.addAttribute("username", username);
        } else {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            model.addAttribute("username", oAuth2User.getAttributes().get("email"));
        }
        logger.debug("[add curve point] authentication name: " + username);

        Collection<CurvePoint> curvePoints = curvePointReadService.getCurvePoints();
        model.addAttribute("curvePoints", curvePoints);
        logger.debug("[reading curve point] curve point: " + curvePoints);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {

        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute(curvePoint);
        logger.debug("[adding curve point] curve point: " + curvePoint);

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        model.addAttribute(curvePoint);
        logger.debug("[validating curve point] curve point: " + curvePoint);
        curvePointCreationService.createCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        CurvePoint curvePoint = curvePointReadService.getCurvePoint(id);
        logger.debug("[updating curve point form] show update form id: " + id);
        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        logger.debug("[updating curve point]  curve point: " + curvePoint);
        curvePointUpdateService.updateCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    @DeleteMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {

        logger.debug("[deleting curve point]  id: " + id);
        curvePointDeletionService.deleteCurvePointById(id);

        return "redirect:/curvePoint/list";
    }

    @DeleteMapping("/curvePoint/delete")
    public String deleteAll() {

        logger.debug("[deleting curve point] curve points: all");
        curvePointDeletionService.deleteCurvePoints();

        return "redirect:/curvePoint/list";
    }
}
