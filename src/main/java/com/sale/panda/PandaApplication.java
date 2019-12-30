package com.sale.panda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sale.panda")
@MapperScan(basePackages={"com.sale.panda.dao"})
public class PandaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandaApplication.class, args);
    }

}
