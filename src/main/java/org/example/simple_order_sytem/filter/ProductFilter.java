package org.example.simple_order_sytem.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.simple_order_sytem.entity.Product;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter implements Specification<Product> {
    private Integer id;
    private Integer productLineId;
    private String name;
    private Integer scale;
    private String vendor;
    private String PDTDescription;
    private Integer QtylnStock;
    private Double byPrice;
    private String MSRP;

    @Override
    public Predicate toPredicate(@NonNull Root<Product> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
        }
        if (productLineId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("productLineId"), productLineId));
        }
        if (name != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (scale != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("scale"), scale));
        }
        if (vendor != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("vendor")), "%" + vendor.toLowerCase() + "%"));
        }
        if (PDTDescription != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("PDTDescription")), "%" + PDTDescription.toLowerCase() + "%"));
        }
        if (QtylnStock != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("QtylnStock"), QtylnStock));
        }
        if (byPrice != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("byPrice"), byPrice));
        }
        if (MSRP != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("MSRP")), "%" + MSRP.toLowerCase() + "%"));
        }
        return predicate;
    }
}
