package org.hope.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @GetMapping("/hi")
    public String hi(){
        return this.from;
    }
}
