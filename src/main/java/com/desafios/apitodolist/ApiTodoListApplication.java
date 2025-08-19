package com.desafios.apitodolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiTodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTodoListApplication.class, args);
    }

}
