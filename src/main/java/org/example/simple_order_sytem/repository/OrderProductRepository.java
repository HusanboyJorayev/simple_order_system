package org.example.simple_order_sytem.repository;

import org.example.simple_order_sytem.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer>, JpaSpecificationExecutor<OrderProduct> {
    List<OrderProduct> findByProductId(Integer pId);

    List<OrderProduct> findByOrderId(Integer oId);
}
