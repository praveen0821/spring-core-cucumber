package com.programcreek.helloworld.controller;

import com.programcreek.helloworld.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	String message = "Welcome to Spring MVC!";

	@Autowired
	private EmployeeManager employeeManager;

	@GetMapping("/hello")
	public ModelAndView showMessage() {
		System.out.println("in HelloWorldController");

		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		
		return mv;
	}

	@GetMapping("/hellocntrl")
	public ResponseEntity<String> helloGetControl() {
		return new ResponseEntity("Hello get control response", HttpStatus.OK);
	}

}
