package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.PaymentDto;
import org.example.simple_order_sytem.entity.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    Payment toEntity(PaymentDto dto);

    PaymentDto toDto(Payment entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(PaymentDto dto, @MappingTarget Payment entity);
}
