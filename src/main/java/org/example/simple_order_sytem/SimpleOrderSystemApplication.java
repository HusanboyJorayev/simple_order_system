package org.example.simple_order_sytem;

import org.example.simple_order_sytem.entity.*;
import org.example.simple_order_sytem.repository.*;
import org.example.simple_order_sytem.status.OrderStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;


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

    @Bean
    public CommandLineRunner runOrder(OrderRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                Order order = Order.builder()
                        .customerId(i / 3 + 1)
                        .requiredDate(LocalDate.now().plusMonths(3))
                        .orderDate(LocalDate.now())
                        .shippedDate(LocalDate.now().plusDays(27))
                        .comments("comments => " + i / 3 + 2)
                        .createdAt(Instant.now())
                        .build();
                if (i % 3 == 0) {
                    order.setStatus(OrderStatus.ONLINE);
                } else if (i % 3 == 1) {
                    order.setStatus(OrderStatus.OFFLINE);
                } else if (i % 8 == 2) {
                    order.setStatus(OrderStatus.BLOCKED);
                } else
                    order.setStatus(OrderStatus.UNBLOCKED);
                repository.save(order);
            }
        };
    }

    @Bean
    public CommandLineRunner runCustomer(CustomerRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                Customer customer = Customer.builder()
                        .employeeId(i / 3 + 1)
                        .firstName("firstName_" + i)
                        .country("Country_" + i)
                        .lastName("lastName_" + i)
                        .address1("address1_" + i)
                        .address2("address2_" + i)
                        .zipCode("zipCode_" + i)
                        .city("city_" + i)
                        .creditLimit(23.5 + (double) i / 3 * 4)
                        .email("email_@" + i)
                        .phone("phone_" + i)
                        .state("state_" + i)
                        .createdAt(LocalDateTime.now())
                        .build();
                repository.save(customer);
            }
        };
    }

    @Bean
    public CommandLineRunner runEmployee(EmployeeRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                Employee employee = Employee.builder()
                        .officeId(i + 1)
                        .extension("extension_" + i)
                        .firstname("firstname_" + i)
                        .lastname("lastname_" + i)
                        .email("email-" + i)
                        .jobTitle("job_title_" + i)
                        .reportTo(i / 3 / 5 + 1)
                        .createdAt(LocalDateTime.now())
                        .build();
                repository.save(employee);
            }
        };
    }

    @Bean
    public CommandLineRunner runOffice(OfficeRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                Office office = Office.builder()
                        .address1("address1_" + i)
                        .address2("address2_" + i)
                        .city("city_" + i)
                        .country("Country_" + i)
                        .state("state_" + i)
                        .phone("phone_" + i)
                        .postCode(i + 34)
                        .territory("territory_" + i)
                        .createdAt(LocalDateTime.now())
                        .build();
                repository.save(office);
            }
        };
    }

    @Bean
    public CommandLineRunner runPayment(PaymentRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                Payment payment = Payment.builder()
                        .amount(i + 3 * 4.6)
                        .customerId(i / 3 + 1)
                        .paymentDate(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .build();
                repository.save(payment);
            }
        };
    }
}
