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
    public String home(Model model)
    {
        Collection<CurvePoint> curvePoints = curvePointReadService.getCurvePoints();
        model.addAttribute("curvePoints", curvePoints);
        logger.debug("[home] curvePoints: " + curvePoints);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        return "redirect:/curvePoint/list";
    }
}
