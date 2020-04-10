package com.example.demo;

import com.example.demo.controller.ItemsController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication       //排除此类的autoconfig
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})//排除aoto
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        //SpringApplication.run(ItemsController.class, args);
    }

}
