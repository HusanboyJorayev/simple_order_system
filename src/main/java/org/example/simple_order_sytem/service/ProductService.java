package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.ProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    Response<ProductDto> create(ProductDto productLineDto);

    Response<ProductDto> get(Integer id);

    Response<ProductDto> update(ProductDto productLineDto, Integer id);

    Response<ProductDto> delete(Integer id);

    Response<List<ProductDto>> getAll();

    Response<Page<ProductDto>> getPAge(Integer page, Integer size);
}
