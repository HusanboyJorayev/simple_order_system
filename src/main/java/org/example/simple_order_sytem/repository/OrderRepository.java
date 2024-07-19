package org.example.simple_order_sytem.repository;

import org.example.simple_order_sytem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query(value = "select o.id, o.customer_id, o.order_date, o.required_date, o.shipped_date\n" +
            "from orders as o", nativeQuery = true)
    List<Object[]> shortOrderDto();
}
