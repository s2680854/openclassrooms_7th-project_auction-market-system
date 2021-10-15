package com.nnk.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {

		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {

		return "redirect:/bidList/list";
	}

	@RequestMapping("/*")
	public String getGithub(Principal user) {

		return "redirect:/bidList/list";
	}
}
