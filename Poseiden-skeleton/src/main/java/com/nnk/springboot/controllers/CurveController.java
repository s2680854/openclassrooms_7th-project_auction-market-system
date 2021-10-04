package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.curvepoint.CurvePointCreationService;
import com.nnk.springboot.service.curvepoint.CurvePointDeletionService;
import com.nnk.springboot.service.curvepoint.CurvePointReadService;
import com.nnk.springboot.service.curvepoint.CurvePointUpdateService;
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

        Collection<CurvePoint> curvePoints = curvePointReadService.getCurvePoints();
        model.addAttribute("curvePoints", curvePoints);
        logger.debug("[home] curvePoints: " + curvePoints);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {

        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute(curvePoint);
        logger.debug("[add] curve point: " + curvePoint);

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        model.addAttribute(curvePoint);
        logger.debug("[validate] curve point: " + curvePoint);
        curvePointCreationService.createCurvePoint(curvePoint);

        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        CurvePoint curvePoint = curvePointReadService.getCurvePoint(id);
        logger.debug("[get update] curve point: " + curvePoint);
        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        logger.debug("[post update] curve point: " + curvePoint);
        curvePointUpdateService.updateCurvePoint(curvePoint);

        return "redirect:/curvePoint/list";
    }

    @DeleteMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {

        curvePointDeletionService.deleteCurvePointById(id);

        return "redirect:/curvePoint/list";
    }
}
