package org.hope.feignconsumer.controller;

import org.hope.feignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hope.feignconsumer.entity.User;

@RestController
public class ConsumerController {
    @Autowired
    private HelloService helloService;

    @RequestMapping(value ="/feign-consumer",method = RequestMethod.GET)
    public String helloController(){
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
    public String helloConsumer2(){
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("psw")).append("\n");
        sb.append(helloService.hello("psw",20)).append("\n");
        sb.append(helloService.hello(new User("psw",20))).append("\n");
        return sb.toString();
    }
}
