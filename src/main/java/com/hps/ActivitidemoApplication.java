package com.hps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class,SecurityAutoConfiguration.class})
@MapperScan("com.hps.dao")
public class ActivitidemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivitidemoApplication.class, args);
    }
}
