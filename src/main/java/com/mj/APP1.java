package com.mj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mj.mapper"})
public class APP1 {

    public static void main(String[] args) {
        SpringApplication.run(APP1.class);
    }



}
