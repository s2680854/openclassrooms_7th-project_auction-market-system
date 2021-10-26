package com.nnk.springboot.controller;

import com.nnk.springboot.configuration.SpringSecurityConfig;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	private Logger logger = LogManager.getLogger(LoginController.class);
	private SpringSecurityConfig springSecurityConfig;

	@RequestMapping("/")
	public String home(Model model) {

		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {

		return "redirect:/bidList/list";
	}

	@RequestMapping("/*")
	public String getGithub(Principal user, Model model) {


		logger.debug("GitHub name: " + user.getName());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		String username = oAuth2User.getAttributes().get("email").toString();
		String name = oAuth2User.getAttributes().get("name").toString();

		logger.debug("[github-login] email: " + username);
		logger.debug("[github-login] name: " + name);
		logger.debug("[github-login] optional: " + userRepository.findByEmail(username));
		if (userRepository.findByEmail(username) != null) {
			return "redirect:/login";
		}

		return "redirect:/user/add";
	}
}
