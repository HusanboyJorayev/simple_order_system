package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.ProductLineDto;
import org.example.simple_order_sytem.entity.ProductLine;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductLineMapper {
    ProductLineDto toDto(ProductLine productLine);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductLine toEntity(ProductLineDto productLineDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductLine(ProductLineDto productLineDto, @MappingTarget ProductLine productLine);
}
