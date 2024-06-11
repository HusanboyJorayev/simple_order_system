package org.example.simple_order_sytem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.ProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.entity.Product;
import org.example.simple_order_sytem.filter.ProductFilter;
import org.example.simple_order_sytem.mapper.ProductMapper;
import org.example.simple_order_sytem.repository.ProductRepository;
import org.example.simple_order_sytem.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Response<ProductDto> create(ProductDto productLineDto) {
        Product entity = this.productMapper.toEntity(productLineDto);
        entity.setCreatedAt(Instant.now());
        this.productRepository.save(entity);
        return Response.<ProductDto>builder()
                .message("Product created")
                .status(HttpStatus.CREATED)
                .data(productMapper.toDto(entity))
                .build();
    }

    @Override
    public Response<ProductDto> get(Integer id) {
        Product product = this.productRepository.findById(id).
                orElseThrow();
        return Response.<ProductDto>builder()
                .message("Product found")
                .status(HttpStatus.OK)
                .data(productMapper.toDto(product))
                .build();
    }

    @Override
    public Response<ProductDto> update(ProductDto productLineDto, Integer id) {
        Product product = this.productRepository.findById(id).orElseThrow();
        product.setUpdatedAt(Instant.now());
        this.productMapper.updateD(productLineDto, product);
        this.productRepository.save(product);
        return Response.<ProductDto>builder()
                .message("Product updated")
                .status(HttpStatus.OK)
                .data(productMapper.toDto(product))
                .build();
    }

    @Override
    public Response<ProductDto> delete(Integer id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow();
        product.setDeletedAt(Instant.now());
        if (product.getDeletedAt() != null) {
            this.productRepository.delete(product);
            return Response.<ProductDto>builder()
                    .message("Product deleted")
                    .status(HttpStatus.OK)
                    .data(productMapper.toDto(product))
                    .build();
        }
        return Response.<ProductDto>builder()
                .code(-1)
                .message("Product not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<List<ProductDto>> getAll() {
        List<ProductDto> dtoList = this.productRepository.findAll().
                stream().map(this.productMapper::toDto).toList();
        if (dtoList.isEmpty()) {
            return Response.<List<ProductDto>>builder()
                    .code(-1)
                    .message("No products found")
                    .build();
        }
        return Response.<List<ProductDto>>builder()
                .message("Products found")
                .status(HttpStatus.OK)
                .data(dtoList)
                .build();
    }

    @Override
    public Response<Page<ProductDto>> getPAge(Integer page, Integer size) {
        Page<ProductDto> pages = this.productRepository.findAll(PageRequest.of(page, size))
                .map(this.productMapper::toDto);
        if (pages.isEmpty()) {
            return Response.<Page<ProductDto>>builder()
                    .code(-1)
                    .message("No products found")
                    .build();
        }
        return Response.<Page<ProductDto>>builder()
                .message("Products found")
                .status(HttpStatus.OK)
                .data(pages)
                .build();
    }

    @Override
    public Response<List<ProductDto>> getFilter(Integer id, Integer productLineId, String name,
                                                Integer scale, String vendor, String PDTDescription,
                                                Integer QtylnStock, Double byPrice, String MSRP) {
        Specification<Product> specification = new ProductFilter(id, productLineId, name, scale, vendor, PDTDescription, QtylnStock, byPrice, MSRP);
        List<ProductDto> products = this.productRepository.findAll(specification).
                stream().map(this.productMapper::toDto).toList();
        return Response.<List<ProductDto>>builder()
                .message("Products found")
                .status(HttpStatus.OK)
                .data(products)
                .build();
    }
}
