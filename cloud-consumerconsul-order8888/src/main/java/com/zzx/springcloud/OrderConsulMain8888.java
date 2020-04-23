package com.zzx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain8888 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain8888.class, args);
    }
}
