package com.example.demo765;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo765.mapper")
public class Demo765Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo765Application.class, args);
    }

}
