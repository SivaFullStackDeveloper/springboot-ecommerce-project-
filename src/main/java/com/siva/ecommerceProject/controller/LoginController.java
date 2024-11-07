package com.siva.ecommerceProject.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/homePage")
    public String homePage() {
        return "Hello welcome Home Page";
    }
}
