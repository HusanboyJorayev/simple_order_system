package org.example.simple_order_sytem.dto;


import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int id;
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
