package org.example.pokeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PokeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokeAppApplication.class, args);
    }

}
