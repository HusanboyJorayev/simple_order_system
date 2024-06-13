package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    Response<CustomerDto> create(CustomerDto dto);

    Response<CustomerDto> get(Integer id);

    Response<CustomerDto> update(CustomerDto dto, Integer id);

    Response<CustomerDto> delete(Integer id);

    Response<List<CustomerDto>>getAll();

    Response<Page<CustomerDto>>getPage(Integer page, Integer size);
}
