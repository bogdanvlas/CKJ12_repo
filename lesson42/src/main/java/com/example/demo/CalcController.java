package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calc")
public class CalcController {
	@GetMapping("/")
	public String calc(@RequestParam(name = "op1") Integer op1, @RequestParam(name = "op2") Integer op2,
			@RequestParam(name = "op") String op, Model model) {
		int result = 0;
		switch (op) {
		case "sum":
			result = op1 + op2;
			break;
		case "sub":
			result = op1 - op2;
			break;
		case "mul":
			result = op1 * op2;
			break;
		case "div":
			result = op1 / op2;
			break;
		}
		model.addAttribute("result", result);
		return "calc";
	}
}
