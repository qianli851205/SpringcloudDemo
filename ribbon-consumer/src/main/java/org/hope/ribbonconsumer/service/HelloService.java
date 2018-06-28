package org.hope.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        /**
         * 两种方式
         * （1） restTemplate.getForEntity
         * （2） restTemplate.getForObject
         */
        //return restTemplate.getForEntity("http://SERVICE-HI/hello",String.class).getBody();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://SERVICE-HI/hello",String.class);
        String body = responseEntity.getBody();
        return body;
    }

    @HystrixCommand(fallbackMethod = "heloFallbackSec")
    public String helloFallback(){
        return "first error";
    }

    public String heloFallbackSec(){
        return "second error";
    }


}
