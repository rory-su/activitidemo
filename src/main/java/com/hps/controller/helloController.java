package com.hps.controller;



import org.activiti.engine.IdentityService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {


    @RequestMapping("hello")
    public String hello(){

        return "hello";
    }
}
