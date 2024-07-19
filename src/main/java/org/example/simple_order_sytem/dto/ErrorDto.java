package org.example.simple_order_sytem.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorDto {
    private String message;
    private String code;
}
