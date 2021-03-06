package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.user.UserCreationService;
import com.nnk.springboot.service.user.UserDeletionService;
import com.nnk.springboot.service.user.UserReadService;
import com.nnk.springboot.service.user.UserUpdateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

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

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            logger.debug("[github-login] authentication name: " + authentication.getName());
            if (!authentication.getName().contains("@")) {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                String username = oAuth2User.getAttributes().get("email").toString();
                String name = oAuth2User.getAttributes().get("name").toString();
                logger.debug("[github-login] email: " + username);
                logger.debug("[github-login] name: " + name);
                user.setUsername(username);
                user.setFullname(name);
            }
        } catch (Exception e) {logger.debug("[github-login] oauth2 failed");}

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
        return "redirect:/login";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        Optional<User> optional = userReadService.getUser(id);
        User user = new User();
        if (optional.isPresent()) {
            user.setUsername(optional.get().getUsername());
            user.setFullname(optional.get().getFullname());
            user.setRole(optional.get().getRole());
        }

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

        userDeletionService.deleteUserById(id);
        model.addAttribute("users", userReadService.getUsers());
        return "redirect:/user/list";
    }

    @DeleteMapping("/user/delete")
    public String deleteAll() {

        userDeletionService.deleteUsers();

        return "redirect:/user/list";
    }
}
