package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CustomerService {
    Response<CustomerDto> create(CustomerDto dto);

    Response<CustomerDto> get(Integer id);

    Response<CustomerDto> update(CustomerDto dto, Integer id);

    Response<CustomerDto> delete(Integer id);

    Response<List<CustomerDto>> getAll();

    Response<List<CustomerDto>> getFilter(Integer id,
                                          Integer employeeId,
                                          String firstName,
                                          String lastName,
                                          String email,
                                          String phone,
                                          String address1,
                                          String address2,
                                          String city,
                                          String state,
                                          String zipCode,
                                          String country,
                                          Double creditLimit);

    Response<List<CustomerDto>> getFilterByQuery(Integer id,
                                                 Integer employeeId,
                                                 String firstName,
                                                 String lastName,
                                                 String email,
                                                 String phone,
                                                 String address1,
                                                 String address2,
                                                 String city,
                                                 String state,
                                                 String zipCode,
                                                 String country,
                                                 Double creditLimit,
                                                 Integer page,
                                                 Integer size);

    Response<Page<CustomerDto>> getPage(Integer page, Integer size);

    Response<Map<Integer, List<CustomerDto>>> group_byCustomerByEmployeeId();
}
