package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.ProductDto;
import org.example.simple_order_sytem.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "updatedAt")
    Product toEntity(ProductDto productDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateD(ProductDto productDto, @MappingTarget Product product);
}
