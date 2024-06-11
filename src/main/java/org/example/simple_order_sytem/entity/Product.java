package org.example.simple_order_sytem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productLineId;
    private String name;
    private Integer scale;
    private String vendor;
    private String PDTDescription;
    private Integer QtylnStock;
    private Double byPrice;
    private String MSRP;
    private Instant createdAt;
    private Instant deletedAt;
    private Instant updatedAt;

}

