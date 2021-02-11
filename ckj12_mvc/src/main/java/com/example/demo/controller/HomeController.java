package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	private UserRepository userRepository;

	@Autowired
	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	public String home(Model model, Principal principal) {
		if (principal != null) {
			// юзернейм (уникальный)
			String username = principal.getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("message", "Welcome, " + user.getName());
		} else {
			model.addAttribute("message", "Welcome to my app!");
		}
		return "home";
	}

	@GetMapping("/login")
	public String signIn() {
		return "login";
	}
}
