package org.example.simple_order_sytem.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.simple_order_sytem.entity.Office;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
@AllArgsConstructor
public class OfficeFilter implements Specification<Office> {
    private Integer id;
    private String city;
    private String phone;
    private String address1;
    private String address2;
    private String state;
    private String country;
    private Integer postCode;
    private String territory;

    @Override
    public Predicate toPredicate(@NonNull Root<Office> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (id != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
        }
        if (city != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
        }
        if (phone != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + phone.toLowerCase() + "%"));
        }
        if (address1 != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("address1")), "%" + address1.toLowerCase() + "%"));
        }
        if (address2 != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("address2")), "%" + address2.toLowerCase() + "%"));
        }
        if (state != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("state")), "%" + state.toLowerCase() + "%"));
        }
        if (country != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("country")), "%" + country.toLowerCase() + "%"));
        }
        if (postCode != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("postCode"), postCode));
        }
        if (territory != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("territory")), "%" + territory.toLowerCase() + "%"));
        }
        return predicate;
    }
}
