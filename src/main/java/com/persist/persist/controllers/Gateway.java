package com.persist.persist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Gateway {


    @GetMapping("/api/gateway")
    public String gateway(){
        return "test.jsp";
    }


}
