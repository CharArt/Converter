package com.charartpav.converte.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*@author Artem Charykov*/

@Controller
public class LoginController {
    @GetMapping("/")
    public String loginInput (){
        return "login";
    }
}