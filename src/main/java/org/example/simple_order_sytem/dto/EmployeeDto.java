package org.example.simple_order_sytem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    private Integer id;
    private Integer officeId;
    private Integer reportTo;
    private String lastname;
    private String firstname;
    private String extension;
    private String email;
    private String jobTitle;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public EmployeeDto(int id, int officeId, int reportTo, String lastname, String firstname, String extension, String email, String jobTitle) {
        this.id = id;
        this.officeId = officeId;
        this.reportTo = reportTo;
        this.lastname = lastname;
        this.firstname = firstname;
        this.extension = extension;
        this.email = email;
        this.jobTitle = jobTitle;
    }
}
