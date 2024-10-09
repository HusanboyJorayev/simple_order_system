package org.example.simple_order_sytem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto implements Serializable {
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

    public CustomerDto(Integer id, Integer employeeId, String firstName,
                       String lastName, String email, String phone,
                       String address1, String address2,
                       String city, String state,
                       String zipCode, String country,
                       Double creditLimit) {
        this.id = id;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.creditLimit = creditLimit;
    }
}
