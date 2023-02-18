package com.javacodegeeks.example.controllers;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

	private final AtomicInteger counter = new AtomicInteger(0);
	
	@GetMapping("/counter")
	public String myView(Map model) {
		counter.set(counter.get()+1);
		model.put("counter", counter.get());
		return "view-page";
	}
}
