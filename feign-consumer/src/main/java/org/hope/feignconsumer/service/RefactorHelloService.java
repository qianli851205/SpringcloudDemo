package org.hope.feignconsumer.service;

import org.hope.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("service-hi")
public interface RefactorHelloService extends HelloService {
}
