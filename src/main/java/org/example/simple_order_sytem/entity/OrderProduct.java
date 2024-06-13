package org.example.simple_order_sytem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double price;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
