package com.fzucai.yunsign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.fzucai.yunsign.mapper")
public class YunsignApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunsignApplication.class, args);
    }

}
