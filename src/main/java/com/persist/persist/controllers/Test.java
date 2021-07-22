package com.persist.persist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
	@GetMapping("/createRequest/gateway}")
	public String gateway() {


		return "test.jsp";
	}
}
