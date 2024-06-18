package org.example.simple_order_sytem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.dto.OrderDto;
import org.example.simple_order_sytem.dto.OrderProductDto;
import org.example.simple_order_sytem.dto.Response;
import org.example.simple_order_sytem.entity.Order;
import org.example.simple_order_sytem.filter.OrderFilter;
import org.example.simple_order_sytem.mapper.OrderMapper;
import org.example.simple_order_sytem.mapper.OrderProductMapper;
import org.example.simple_order_sytem.repository.OrderProductRepository;
import org.example.simple_order_sytem.repository.OrderRepository;
import org.example.simple_order_sytem.service.OrderService;
import org.example.simple_order_sytem.status.OrderStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Lazy
    private final OrderProductRepository orderProductRepository;
    @Lazy
    private final OrderProductMapper orderProductMapper;

    @Override
    public Response<OrderDto> create(OrderDto dto) {
        Order order = this.orderMapper.toEntity(dto);
        order.setCreatedAt(Instant.now());
        this.orderRepository.save(order);
        return Response.<OrderDto>builder()
                .message("Order created successfully")
                .status(HttpStatus.CREATED)
                .data(this.orderMapper.toDto(order))
                .build();
    }

    @Override
    public Response<OrderDto> get(Integer id) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return Response.<OrderDto>builder()
                    .message("Order found")
                    .data(this.orderMapper.toDto(order))
                    .status(HttpStatus.OK)
                    .build();
        }
        return Response.<OrderDto>builder()
                .message("Order not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<OrderDto> update(OrderDto dto, Integer id) {
        Optional<Order> optional = this.orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            order.setUpdatedAt(Instant.now());
            this.orderMapper.update(dto, order);
            this.orderRepository.save(order);
            return Response.<OrderDto>builder()
                    .message("Order updated successfully")
                    .status(HttpStatus.OK)
                    .data(this.orderMapper.toDto(order))
                    .build();
        }
        return Response.<OrderDto>builder()
                .message("Order not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @Override
    public Response<OrderDto> delete(Integer id) {
        return this.orderRepository.findById(id)
                .map(order -> {
                    order.setDeletedAt(Instant.now());
                    this.orderRepository.delete(order);
                    return Response.<OrderDto>builder()
                            .message("Order deleted successfully")
                            .status(HttpStatus.OK)
                            .data(this.orderMapper.toDto(order))
                            .build();
                })
                .orElse(Response.<OrderDto>builder()
                        .message("Order not found")
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @Override
    public Response<List<OrderDto>> getAll() {
        List<Order> orderList = this.orderRepository.findAll();
        if (!orderList.isEmpty()) {
            return Response.<List<OrderDto>>builder()
                    .message("Orders found")
                    .status(HttpStatus.OK)
                    .data(orderList.stream().map(this.orderMapper::toDto).toList())
                    .build();
        }
        return Response.<List<OrderDto>>builder()
                .message("Orders are empty")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public Response<Page<OrderDto>> getPage(Integer page, Integer size) {
        Page<OrderDto> dtoPage = this.orderRepository.findAll(PageRequest.of(page, size))
                .map(this.orderMapper::toDto);
        return Response.<Page<OrderDto>>builder()
                .message("Orders found")
                .status(HttpStatus.OK)
                .data(dtoPage)
                .build();
    }

    @Override
    public Response<List<OrderDto>> getFilter(Integer id, Integer customerId, Integer orderProductId,
                                              LocalDate orderDate, LocalDate requiredDate,
                                              LocalDate shippedDate, OrderStatus status, String comments) {
        Specification<Order> specification = new OrderFilter(id, customerId, orderProductId, orderDate, requiredDate, shippedDate, status, comments);
        List<OrderDto> dtoList = this.orderRepository.findAll(specification)
                .stream().map(this.orderMapper::toDtoWithOrderProduct).toList();
        if (!dtoList.isEmpty()) {
            return Response.<List<OrderDto>>builder()
                    .message("Orders found")
                    .status(HttpStatus.OK)
                    .data(dtoList)
                    .build();
        }
        return Response.<List<OrderDto>>builder()
                .message("Orders are empty")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public Response<Map<OrderStatus, List<OrderDto>>> group_byOrderByStatus() {
        Map<OrderStatus, List<OrderDto>> collect = this.orderRepository.findAll()
                .stream().map(this.orderMapper::toDto)
                .collect(Collectors.groupingBy(OrderDto::getStatus));
        return Response.<Map<OrderStatus, List<OrderDto>>>builder()
                .message("Orders found")
                .status(HttpStatus.OK)
                .data(collect)
                .build();
    }

    @Override
    public Response<OrderDto> getWithOrderProduct(Integer id) {
        Optional<Order> optional = this.orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            OrderDto dto = this.orderMapper.toDtoWithOrderProduct(order);
            List<OrderProductDto> dtoList = this.orderProductRepository.findByOrderId(id)
                    .stream().map(this.orderProductMapper::toDto).toList();
            dto.setOrderProducts(dtoList);
            return Response.<OrderDto>builder()
                    .message("Order found")
                    .status(HttpStatus.OK)
                    .data(dto)
                    .build();
        }
        return Response.<OrderDto>builder()
                .message("Order not found")
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
