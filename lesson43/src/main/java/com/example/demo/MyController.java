package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	private List<User> users = new ArrayList<>();

	@GetMapping("/info")
	public String getInfo(@RequestParam(name = "param") String param, Model model) {
		System.out.println(param);
		model.addAttribute("my_param", param);
		return "info";
	}

	@GetMapping("/create_user")
	public String createUser() {
		return "user_form";
	}

	@PostMapping("/add_user")
	public String addUser(@ModelAttribute(name = "user") User user, Model model) {
		//вычислить ид для пользователя
		users.add(user);
		return "redirect:/users";
	}

	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", users);
		return "users";
	}
}
