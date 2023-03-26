package org.csu.petstoremanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org/csu/petstoremanage/mapper")
public class PetstoreManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetstoreManageApplication.class, args);
    }

}
