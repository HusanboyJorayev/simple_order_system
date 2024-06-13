package org.example.simple_order_sytem;

import org.example.simple_order_sytem.entity.OrderProduct;
import org.example.simple_order_sytem.entity.Product;
import org.example.simple_order_sytem.entity.ProductLine;
import org.example.simple_order_sytem.repository.OrderProductRepository;
import org.example.simple_order_sytem.repository.ProductLineRepository;
import org.example.simple_order_sytem.repository.ProductRepository;
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

    @Bean
    public CommandLineRunner runProduct(ProductRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                Product product = Product.builder()
                        .scale(i + i)
                        .byPrice(2.5 * i)
                        .productLineId(i / 2 + 1)
                        .MSRP("MSRP=>" + i)
                        .name("name" + i)
                        .PDTDescription("description" + i)
                        .vendor("vendor" + i)
                        .QtylnStock(i + 1)
                        .createdAt(Instant.now())
                        .build();
                repository.save(product);
            }
        };
    }

    @Bean
    public CommandLineRunner runOrderProduct(OrderProductRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                OrderProduct orderProduct = OrderProduct.builder()
                        .productId(i / 2 + 1)
                        .orderId(i / 3 + 1)
                        .price(i * i + 2.98)
                        .quantity(i + 4)
                        .createdAt(Instant.now())
                        .build();
                repository.save(orderProduct);
            }
        };
    }
}
