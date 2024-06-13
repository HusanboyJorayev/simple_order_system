package org.example.simple_order_sytem.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.simple_order_sytem.entity.OrderProduct;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
@AllArgsConstructor
public class OrderProductFilter implements Specification<OrderProduct> {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double price;

    @Override
    public Predicate toPredicate(@NonNull Root<OrderProduct> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
        }
        if (orderId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("orderId"), orderId));
        }
        if (productId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("productId"), productId));
        }
        if (quantity != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("quantity"), quantity));
        }
        if (price != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("price"), price));
        }
        return predicate;
    }
}
