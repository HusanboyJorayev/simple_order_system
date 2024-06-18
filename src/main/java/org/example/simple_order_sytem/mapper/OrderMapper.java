package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.OrderDto;
import org.example.simple_order_sytem.entity.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(ignore = true, target = "orderProducts")
    OrderDto toDto(Order order);

    OrderDto toDtoWithOrderProduct(Order order);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "orderProducts")
    Order toEntity(OrderDto orderDto);

    @Mapping(ignore = true, target = "orderProducts")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(OrderDto orderDto, @MappingTarget Order order);
}
