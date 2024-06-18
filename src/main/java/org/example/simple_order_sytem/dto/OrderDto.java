package org.example.simple_order_sytem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.simple_order_sytem.entity.OrderProduct;
import org.example.simple_order_sytem.status.OrderStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
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

    private List<OrderProductDto> orderProducts;

}
