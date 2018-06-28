package org.hope.ribbonconsumer.controller;

import org.hope.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    /*
    @Autowired
    RestTemplate restTemplate;
    @GetMapping(value="/ribbon-consumer")
    public String helloConsumer(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://SERVICE-HI/hello",String.class);
        String body = responseEntity.getBody();
        return body;
    }
    */
    @Autowired
    private HelloService helloService;

    @GetMapping(value="/ribbon-consumer")
    public String helloConsumer(){
        return helloService.helloService();
    }
}
