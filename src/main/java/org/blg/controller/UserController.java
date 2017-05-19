package org.blg.controller;

import org.blg.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	private UserService userService;
	
	@RequestMapping(name="users")
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	
	
}
