package org.example.simple_order_sytem.service.impl;

import org.example.simple_order_sytem.filter.ProductLineFilter;
import org.example.simple_order_sytem.mapper.ProductMapper;
import org.example.simple_order_sytem.repository.ProductLineRepository;
import org.example.simple_order_sytem.repository.ProductRepository;
import org.example.simple_order_sytem.service.ProductLineService;
import org.example.simple_order_sytem.mapper.ProductLineMapper;
import org.example.simple_order_sytem.dto.ProductLineDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.example.simple_order_sytem.entity.ProductLine;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductLineServiceImpl implements ProductLineService {
    private final ProductLineRepository productLineRepository;
    private final ProductLineMapper productLineMapper;
    @Lazy
    private final ProductRepository productRepository;
    @Lazy
    private final ProductMapper productMapper;

    @Override
    public Response<ProductLineDto> create(ProductLineDto productLineDto) {
        ProductLine productLine = this.productLineMapper.toEntity(productLineDto);
        productLine.setCreatedAt(Instant.now());
        this.productLineRepository.save(productLine);
        return Response.<ProductLineDto>builder()
                .message("Product line created")
                .data(productLineDto)
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public Response<ProductLineDto> get(Integer id) {
        ProductLine line = this.productLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product line not found"));
        return Response.<ProductLineDto>builder()
                .message("Product line found")
                .data(productLineMapper.toDto(line))
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public Response<ProductLineDto> update(ProductLineDto productLineDto, Integer id) {
        ProductLine line = this.productLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product line not found"));
        line.setUpdatedAt(Instant.now());
        this.productLineMapper.updateProductLine(productLineDto, line);
        this.productLineRepository.save(line);
        return Response.<ProductLineDto>builder()
                .message("Product line updated")
                .data(this.productLineMapper.toDto(line))
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public Response<ProductLineDto> delete(Integer id) {
        Optional<ProductLine> line = this.productLineRepository.findById(id);
        line.ifPresent(this.productLineRepository::delete);
        line.get().setDeletedAt(Instant.now());
        return Response.<ProductLineDto>builder()
                .message("Product line deleted")
                .data(this.productLineMapper.toDto(line.get()))
                .build();
    }

    @Override
    public Response<List<ProductLineDto>> getAll() {
        List<ProductLine> all = this.productLineRepository.findAll();
        if (all.isEmpty()) {
            return Response.<List<ProductLineDto>>builder()
                    .code(-1)
                    .message("No products found")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        return Response.<List<ProductLineDto>>builder()
                .message("Products found")
                .data(all.stream().map(this.productLineMapper::toDto).toList())
                .build();
    }

    @Override
    public Response<Page<ProductLineDto>> getPAge(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ProductLine> pages = this.productLineRepository.findAll(pageRequest);
        return Response.<Page<ProductLineDto>>builder()
                .message("Products found")
                .data(pages.map(this.productLineMapper::toDto))
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public Response<List<ProductLineDto>> getFilter(Integer id, String description, String image) {
        Specification<ProductLine> specification = new ProductLineFilter(id, description, image);
        List<ProductLine> all = this.productLineRepository.findAll(specification);
        if (all.isEmpty()) {
            return Response.<List<ProductLineDto>>builder()
                    .code(-1)
                    .message("No products found")
                    .build();
        }
        return Response.<List<ProductLineDto>>builder()
                .message("Products found")
                .status(HttpStatus.OK)
                .data(all.stream().map(this.productLineMapper::toDto).toList())
                .build();
    }

    @Override
    public Response<ProductLineDto> getWithProduct(Integer id) {
        ProductLine line = this.productLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product line not found"));
        return Response.<ProductLineDto>builder()
                .message("Product line found")
                .status(HttpStatus.OK)
                .data(productLineMapper.toDtoWithProduct(line, productRepository, productMapper))
                .build();
    }
}

