package org.example.simple_order_sytem.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.simple_order_sytem.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFilter implements Specification<Employee> {
    private Integer id;
    private Integer officeId;
    private Integer reportTo;
    private String lastname;
    private String firstname;
    private String extension;
    private String email;
    private String jobTitle;

    @Override
    public Predicate toPredicate(@NonNull Root<Employee> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
        }
        if (officeId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("officeId"), officeId));
        }
        if (reportTo != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("reportTo"), reportTo));
        }
        if (lastname != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("lastname")), "%" + lastname.toLowerCase() + "%"));
        }
        if (firstname != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("firstname")), "%" + firstname.toLowerCase() + "%"));
        }
        if (extension != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("extension")), "%" + extension.toLowerCase() + "%"));
        }
        if (email != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
        }
        if (jobTitle != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("jobTitle")), "%" + jobTitle.toLowerCase() + "%"));
        }
        return predicate;
    }
}
