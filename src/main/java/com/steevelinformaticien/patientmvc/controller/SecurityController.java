package com.steevelinformaticien.patientmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SecurityController {

    @GetMapping(path = "/notAuthorized")
    public String notAuthorize(){
        return "not-authorize";
    }
    @GetMapping(path = "/login")
    public String loging(){
        return "login";
    }
}
