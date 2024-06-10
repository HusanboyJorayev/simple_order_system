package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.ProductLineDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductLineService {
    Response<ProductLineDto> create(ProductLineDto productLineDto);

    Response<ProductLineDto> get(Integer id);

    Response<ProductLineDto> update(ProductLineDto productLineDto, Integer id);

    Response<ProductLineDto> delete(Integer id);

    Response<List<ProductLineDto>> getAll();

    Response<Page<ProductLineDto>> getPAge(Integer page, Integer size);

    Response<List<ProductLineDto>> getFilter(Integer id, String description, String image);
}
