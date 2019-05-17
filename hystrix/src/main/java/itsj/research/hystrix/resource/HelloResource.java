package itsj.research.hystrix.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloResource {

    @HystrixCommand(fallbackMethod = "fallbackHello", commandKey = "hello", groupKey = "hello")
    @GetMapping("/hello")
    public String hello(){

        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("Service failure");
        }
        return "Hello there!";
    }

    public String fallbackHello(Throwable hystrix){
        return "fallback Hello !";
    }
}
