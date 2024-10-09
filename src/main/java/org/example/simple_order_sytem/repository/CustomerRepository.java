package org.example.simple_order_sytem.repository;

import org.example.simple_order_sytem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    @Query(value = "select id from customer where employee_id=?1",nativeQuery = true)
    List<Integer> selectIdsByEmployeeId(Integer empId);
}
