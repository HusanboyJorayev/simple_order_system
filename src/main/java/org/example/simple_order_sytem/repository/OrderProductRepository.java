package org.example.simple_order_sytem.repository;

import org.example.simple_order_sytem.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer>, JpaSpecificationExecutor<OrderProduct> {
}
