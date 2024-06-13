package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.OrderProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderProductService {
    Response<OrderProductDto> create(OrderProductDto dto);

    Response<OrderProductDto> get(Integer id);

    Response<OrderProductDto> delete(Integer id);

    Response<OrderProductDto> update(OrderProductDto dto, Integer id);

    Response<Page<OrderProductDto>> getPage(Integer page, Integer size);

    Response<List<OrderProductDto>> getAll();

    Response<List<OrderProductDto>> getFilter(Integer id, Integer orderId, Integer productId, Integer quantity, Double price);
}
