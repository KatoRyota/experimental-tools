package com.example.employeesapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.invoke.MethodHandles;

@Controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/")
    public String index() {
        logger.info("[Called] index()");
        return "index";
    }
}
