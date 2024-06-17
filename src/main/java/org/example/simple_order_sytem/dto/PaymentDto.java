package org.example.simple_order_sytem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Integer id;
    private Integer customerId;
    private LocalDateTime paymentDate;
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
