package com.mk.xixili.pgsb.adminsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminSessionApplication.class, args);
    }
}
