package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{

		return "redirect:/bidList/list";
	}

	@RequestMapping("/*")
	public String getGithub(Principal user){

		/*@GetMapping("/user")

		public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal)
		{

			return Collections.singletonMap("name", principal.getAttribute("name"));

		}*/

		return "redirect:/bidList/list";
	}
}
