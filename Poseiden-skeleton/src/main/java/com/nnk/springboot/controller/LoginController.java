package com.nnk.springboot.controller;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error(HttpServletRequest request, Model model) {
        ModelAndView mav = new ModelAndView();
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == 403) {
                String errorMessage = "Vous n'êtes pas autorisé à accéder à ces informations.";
                mav.addObject("errorMsg", errorMessage);
                mav.setViewName("403");
            } else if (statusCode == 404) {
                String errorMessage = "La page est introuvable.";
                mav.addObject("errorMsg", errorMessage);
                mav.setViewName("404");
            } else if (statusCode == 401) {
                String errorMessage = "Vous n'êtes pas autorisé à accéder à ces informations.";
                mav.addObject("errorMsg", errorMessage);
                mav.setViewName("401");
            }
        }
        return mav;
    }
}
