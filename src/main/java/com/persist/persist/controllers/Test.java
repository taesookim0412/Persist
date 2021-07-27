package com.persist.persist.controllers;

import com.persist.persist.libraries.connections.Connections;
import com.persist.persist.libraries.connections.Slave;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;

@Controller
public class Test {
	@GetMapping("/createRequest/gateway")
	@ResponseBody
	public HashMap<String, ?> gateway() {
		System.out.println("Hello!");
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("A", "B");
		return res;
//		return "test.jsp";
	}
	@GetMapping("/createRequest/gateway2")
	@ResponseBody
	public HashMap<String, String> gateway2(){
		Connections connections = new Connections();
		HashMap<String, String> res = new HashMap<String, String>();
		Object[] slaves = connections.slaves.keySet().toArray();
		res.put("data", Arrays.toString(slaves));
		return res;
	}

}
