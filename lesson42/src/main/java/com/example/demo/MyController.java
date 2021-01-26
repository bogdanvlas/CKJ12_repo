package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app")
public class MyController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/hello")
	public String greeting(
			@RequestParam
			(name = "msg", required = false, defaultValue = "default message") 
			String message,
			@RequestParam(name = "name", required = false) String name, Model model) {
		model.addAttribute("msg", message);
		return "hello";
	}
}
