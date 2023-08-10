package com.preproject.seb_pre_15;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping
    public String home(){
        return "Hello, World !";
    }
}
