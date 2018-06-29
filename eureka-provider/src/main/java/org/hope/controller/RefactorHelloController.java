package org.hope.controller;

import org.hope.entity.User;
import org.hope.service.HelloService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RefactorHelloController implements HelloService{
    @Override
    public String hello(@RequestParam String name){
        return "Hello"+name;
    }
    @Override
    public User hello(@RequestHeader String name, @RequestHeader Integer age){
        return new User(name,age);
    }
    @Override
    public String hello(@RequestBody User user){
        return "Hello"+user.getName()+","+user.getAge();
    }
}
