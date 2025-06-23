package com.informaticonfig.spring.app1.modulo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Modulo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Modulo2Application.class, args);
        System.out.println("¡Aplicación Spring Boot iniciada! Anda a http://localhost:8080/facturar");

    }

}
