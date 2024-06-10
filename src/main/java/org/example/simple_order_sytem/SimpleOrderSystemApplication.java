package org.example.simple_order_sytem;

import org.example.simple_order_sytem.entity.ProductLine;
import org.example.simple_order_sytem.repository.ProductLineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

@SpringBootApplication
public class SimpleOrderSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleOrderSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner runProductLine(ProductLineRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                ProductLine line = ProductLine.builder()
                        .image("image" + i)
                        .description("description" + i)
                        .createdAt(Instant.now())
                        .build();
                repository.save(line);
            }
        };
    }
}
