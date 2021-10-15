package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.controllers.user.UserCreationService;
import com.nnk.springboot.controllers.user.UserDeletionService;
import com.nnk.springboot.controllers.user.UserReadService;
import com.nnk.springboot.controllers.user.UserUpdateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserCreationService userCreationService;
    @Autowired
    private UserReadService userReadService;
    @Autowired
    private UserUpdateService userUpdateService;
    @Autowired
    private UserDeletionService userDeletionService;


    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userReadService.getUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        logger.debug("[add] user: " + user);

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userCreationService.createUser(user);
            model.addAttribute("users", userReadService.getUsers());
            return "redirect:/user/list";
        }
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userReadService.getUser(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userUpdateService.updateUser(user);
        model.addAttribute("users", userReadService.getUsers());
        return "redirect:/user/list";
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        User user = userReadService.getUser(id);
        userDeletionService.deleteUserById(user.getId());
        model.addAttribute("users", userReadService.getUsers());
        return "redirect:/user/list";
    }

    @DeleteMapping("/user/delete")
    public String deleteAll() {

        userDeletionService.deleteUsers();

        return "redirect:/user/list";
    }
}
