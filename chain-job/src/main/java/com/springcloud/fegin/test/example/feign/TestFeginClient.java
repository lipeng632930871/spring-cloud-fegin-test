package com.springcloud.fegin.test.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="chain-app")
public interface TestFeginClient{

    @GetMapping("test/testApp")
    public String testApp();
}
