package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.ProductDto;
import org.example.simple_order_sytem.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(ignore = true, target = "orderProducts")
    ProductDto toDto(Product product);

    ProductDto toDtoWithOrderProduct(Product product);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "orderProducts")
    Product toEntity(ProductDto productDto);

    @Mapping(ignore = true, target = "orderProducts")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateD(ProductDto productDto, @MappingTarget Product product);
}
