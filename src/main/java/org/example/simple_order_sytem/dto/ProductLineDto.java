package org.example.simple_order_sytem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;

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
}
