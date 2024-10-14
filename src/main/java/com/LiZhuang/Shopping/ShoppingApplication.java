package com.LiZhuang.Shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.LiZhuang.Shopping.mapper")
public class ShoppingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }
}
