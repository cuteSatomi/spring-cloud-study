package com.zzx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8888.class, args);
    }
}
