package com.madoor.madhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.madoor.madhub.mapper")
public class MadhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadhubApplication.class, args);
    }

}
