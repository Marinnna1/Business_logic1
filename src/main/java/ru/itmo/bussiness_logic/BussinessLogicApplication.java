package ru.itmo.bussiness_logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="ru.itmo.bussiness_logic")
public class BussinessLogicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BussinessLogicApplication.class, args);
    }

}
