package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.OrderProductDto;
import org.example.simple_order_sytem.entity.OrderProduct;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    OrderProduct toEntity(OrderProductDto dto);

    OrderProductDto toDto(OrderProduct entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDto(OrderProductDto dto, @MappingTarget OrderProduct entity);
}
