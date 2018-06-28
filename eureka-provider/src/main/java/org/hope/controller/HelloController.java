package org.hope.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @GetMapping(value="hello")
    public String index(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello, host:{}, service_id:{}" , instance.getHost() , instance.getServiceId());
        return "Hello World";
    }
}
