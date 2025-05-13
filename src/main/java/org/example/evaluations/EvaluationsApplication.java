package org.example.evaluations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example.evaluations")
public class EvaluationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaluationsApplication.class, args);
    }

}
