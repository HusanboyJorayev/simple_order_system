package org.example.simple_order_sytem.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    private Integer employeeId;
    private String firstName;
    private String lastName;

    @Pattern(regexp = ".+@.+\\..+")
    private String email;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Double creditLimit;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
