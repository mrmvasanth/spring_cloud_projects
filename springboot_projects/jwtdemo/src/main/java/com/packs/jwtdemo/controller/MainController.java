package com.packs.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/getuser")
    public String getUser(){
        return "Hello";
    }
}
