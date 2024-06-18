package org.example.simple_order_sytem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.simple_order_sytem.status.OrderStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String comments;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "orderId")
    private List<OrderProduct> orderProducts;
}
