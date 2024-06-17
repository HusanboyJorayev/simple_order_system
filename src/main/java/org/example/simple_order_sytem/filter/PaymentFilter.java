package org.example.simple_order_sytem.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.simple_order_sytem.entity.Payment;
import org.springframework.data.jpa.domain.Specification;


@NoArgsConstructor
@AllArgsConstructor
public class PaymentFilter implements Specification<Payment> {
    private Integer         id;
    private Integer customerId;
    private Double      amount;

    @Override
    public Predicate toPredicate(@NonNull Root<Payment> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
        }
        if (customerId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("customerId"), customerId));
        }
        if (amount != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("amount"), amount));
        }
        return predicate;
    }
}
