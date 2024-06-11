package org.example.simple_order_sytem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.simple_order_sytem.entity.Image;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductLineDto {
    private Integer id;
    private String description;
    private String image;
    private Instant createdAt;
    private Instant deletedAt;
    private Instant updatedAt;

    List<ProductDto> products;

}
