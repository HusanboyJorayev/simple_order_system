package org.example.simple_order_sytem.service;

import org.example.simple_order_sytem.dto.ProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ProductService {
    Response<ProductDto> create(ProductDto productLineDto);

    Response<ProductDto> get(Integer id);

    Response<ProductDto> update(ProductDto productLineDto, Integer id);

    Response<ProductDto> delete(Integer id);

    Response<List<ProductDto>> getAll();

    Response<Page<ProductDto>> getPAge(Integer page, Integer size);

    Response<List<ProductDto>> getFilter(Integer id, Integer productLineId,
                                         String name, Integer scale, String vendor, String PDTDescription,
                                         Integer QtylnStock, Double byPrice, String MSRP);

    Response<Map<Integer, List<ProductDto>>> groupByProductLineId();
}
