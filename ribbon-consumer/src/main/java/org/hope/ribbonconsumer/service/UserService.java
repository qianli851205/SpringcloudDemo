package org.hope.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.apache.commons.lang.StringUtils;
import org.hope.ribbonconsumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class UserService{
    @Autowired
    RestTemplate restTemplate;

    /**
     * 请求合并
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {
            @HystrixProperty(name="timeDelayInMilliseconds",value="100")
    })
    public User find(Long id){
        return null;
    }
    @HystrixCommand
    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://USER-SERVEOCE/users?ids={1}",List.class, StringUtils.join(ids,","));
    }

    /**
     * 同步方式 + 服务降级  + 缓存
     * @param id
     * @return
     */
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    @HystrixCommand(fallbackMethod = "defaultUser")
    public User getUserById(Long id){
        User user = restTemplate.getForObject("http://USER-SERVEOCE/users/{1}",User.class,id);
        return user;
    }
    private Long getUserByIdCacheKey(Long id){
        return id;
    }

    /**
     * 异步方式
     * @param id
     * @return
     */
    @CacheResult
    @HystrixCommand
    public Future<User> getUserByIdAsync(@CacheKey("id") final String id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                User user = restTemplate.getForObject("http://USER-SERVEOCE/users/{1}",User.class,id);
                return user;
            }
        };
    }

    // 数据更新，清理缓存
    @CacheRemove(commandKey = "getUserById")
    @HystrixCommand
    public void update(@CacheKey("id") User user){
        restTemplate.postForObject("http://USER-SERVEOCE/users",user,User.class);
    }

    @HystrixCommand(defaultFallback = "defaultUserSec")
    public User defaultUser(){
        // 此处可能是另外一个网络请求来获取，所以有可能失败
        return new User("first fallback");
    }

    public User defaultUserSec(){
        return  new User("second fallback");
    }



}
