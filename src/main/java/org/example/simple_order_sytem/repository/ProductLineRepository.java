package org.example.simple_order_sytem.repository;

import org.example.simple_order_sytem.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Integer>, JpaSpecificationExecutor<ProductLine> {
}
