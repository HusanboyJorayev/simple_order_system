package org.example.simple_order_sytem.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.simple_order_sytem.entity.ProductLine;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
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
