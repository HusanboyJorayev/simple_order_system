package org.example.simple_order_sytem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.OrderProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.entity.OrderProduct;
import org.example.simple_order_sytem.filter.OrderProductFilter;
import org.example.simple_order_sytem.mapper.OrderProductMapper;
import org.example.simple_order_sytem.repository.OrderProductRepository;
import org.example.simple_order_sytem.service.OrderProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImp implements OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final OrderProductMapper orderProductMapper;

    @Override
    public Response<OrderProductDto> create(OrderProductDto dto) {
        OrderProduct entity = this.orderProductMapper.toEntity(dto);
        entity.setCreatedAt(Instant.now());
        this.orderProductRepository.save(entity);
        return Response.<OrderProductDto>builder()
                .message("Order product created")
                .status(HttpStatus.CREATED)
                .data(orderProductMapper.toDto(entity))
                .build();
    }

    @Override
    public Response<OrderProductDto> get(Integer id) {
        OrderProduct orderProduct = this.orderProductRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Order product not found"));
        if (orderProduct == null) {
            return Response.<OrderProductDto>builder()
                    .message("Order product not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return Response.<OrderProductDto>builder()
                .message("Order product found")
                .data(orderProductMapper.toDto(orderProduct))
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public Response<OrderProductDto> delete(Integer id) {
        if (this.orderProductRepository.findById(id)
                .isPresent()) {
            this.orderProductRepository.deleteById(id);
            return Response.<OrderProductDto>builder()
                    .message("Order product deleted")
                    .status(HttpStatus.OK)
                    .data(orderProductMapper.toDto(this.orderProductRepository.findById(id).get()))
                    .build();
        }
        return Response.<OrderProductDto>builder()
                .message("Order product not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<OrderProductDto> update(OrderProductDto dto, Integer id) {
        Optional<OrderProduct> optional = this.orderProductRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setUpdatedAt(Instant.now());
            this.orderProductMapper.updateDto(dto, optional.get());
            this.orderProductRepository.save(optional.get());
            return Response.<OrderProductDto>builder()
                    .message("Order product updated")
                    .status(HttpStatus.OK)
                    .data(orderProductMapper.toDto(optional.get()))
                    .build();
        }
        return Response.<OrderProductDto>builder()
                .message("Order product not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<Page<OrderProductDto>> getPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderProductDto> productDtoPage = this.orderProductRepository.findAll(pageRequest)
                .map(this.orderProductMapper::toDto);
        return Response.<Page<OrderProductDto>>builder()
                .message("Order product found")
                .status(HttpStatus.OK)
                .data(productDtoPage)
                .build();
    }

    @Override
    public Response<List<OrderProductDto>> getAll() {
        List<OrderProductDto> dtoList = this.orderProductRepository.findAll()
                .stream().map(this.orderProductMapper::toDto).toList();
        return Response.<List<OrderProductDto>>builder()
                .message("Order product found")
                .status(HttpStatus.OK)
                .data(dtoList)
                .build();
    }

    @Override
    public Response<List<OrderProductDto>> getFilter(Integer id, Integer orderId, Integer productId,
                                                     Integer quantity, Double price) {
        Specification<OrderProduct> specification = new OrderProductFilter(id, orderId, productId, quantity, price);
        List<OrderProductDto> dtoList = this.orderProductRepository.findAll(specification)
                .stream().map(this.orderProductMapper::toDto).toList();
        return Response.<List<OrderProductDto>>builder()
                .message("Order product found")
                .status(HttpStatus.OK)
                .data(dtoList)
                .build();
    }
}
