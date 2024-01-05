package com.example.retourexperience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication/*(exclude={SecurityAutoConfiguration.class})*/
public class RetourExperienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetourExperienceApplication.class, args);
    }

}
