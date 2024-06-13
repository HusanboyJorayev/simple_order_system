package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.CustomerDto;
import org.example.simple_order_sytem.entity.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    Customer toEntity(CustomerDto dto);

    CustomerDto toDto(Customer entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(CustomerDto dto, @MappingTarget Customer entity);
}
