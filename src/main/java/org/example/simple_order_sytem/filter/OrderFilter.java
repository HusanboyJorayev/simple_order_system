package org.example.simple_order_sytem.filter;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.simple_order_sytem.entity.Order;
import org.example.simple_order_sytem.status.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class OrderFilter implements Specification<Order> {
    private Integer id;
    private Integer customerId;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String comments;

    @Override
    public Predicate toPredicate(@NonNull Root<Order> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
        }
        if (customerId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("customerId"), customerId));
        }
        if (orderDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("orderDate"), orderDate));
        }
        if (requiredDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("requiredDate"), requiredDate));
        }
        if (shippedDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("shippedDate"), shippedDate));
        }
        if (status != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
        }
        if (comments != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("comments")), "%" + comments.toLowerCase() + "%"));
        }
        return predicate;
    }
}
