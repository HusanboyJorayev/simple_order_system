package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.OrderDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.status.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public interface OrderService {
    Response<OrderDto> create(OrderDto dto);

    Response<OrderDto> get(Integer id);

    Response<OrderDto> update(OrderDto dto, Integer id);

    Response<OrderDto> delete(Integer id);

    Response<List<OrderDto>> getAll();

    Response<Page<OrderDto>> getPage(Integer page, Integer size);

    Response<List<OrderDto>> getFilter(Integer id, Integer customerId,
                                       LocalDate orderDate, LocalDate requiredDate,
                                       LocalDate shippedDate, OrderStatus status, String comments);

    Response<Map<OrderStatus, List<OrderDto>>> group_byOrderByStatus();
}
