package com.lovnx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liuzhikun
 */
@SpringBootApplication
public class C_Application {
    public static void main(String[] args) {
        SpringApplication.run(C_Application.class, args);
    }
}
