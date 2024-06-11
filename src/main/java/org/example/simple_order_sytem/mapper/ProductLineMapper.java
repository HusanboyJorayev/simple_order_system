package org.example.simple_order_sytem.mapper;


import org.example.simple_order_sytem.dto.ProductDto;
import org.example.simple_order_sytem.entity.ProductLine;
import org.example.simple_order_sytem.dto.ProductLineDto;
import org.example.simple_order_sytem.repository.ProductRepository;
import org.mapstruct.*;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductLineMapper {

    @Mapping(ignore = true, target = "products")
    ProductLineDto toDto(ProductLine productLine);

    @Mapping(target = "products", expression = "java(p(toDto(productLine),productLine,repository,productMapper))")
    ProductLineDto toDtoWithProduct(ProductLine productLine,
                                    @Context ProductRepository repository,
                                    @Context ProductMapper productMapper);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(ignore = true, target = "products")
    ProductLine toEntity(ProductLineDto productLineDto);

    @Mapping(ignore = true, target = "products")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductLine(ProductLineDto productLineDto, @MappingTarget ProductLine productLine);

    @AfterMapping
    default List<ProductDto> p(@MappingTarget ProductLineDto dto, ProductLine line,
                               @Context ProductRepository repository,
                               @Context ProductMapper mapper) {
        List<ProductDto> dtoList = repository.findByProductLineId(line.getId())
                .stream()
                .map(mapper::toDto)
                .toList();
        dto.setProducts(dtoList);
        return dtoList;
    }
}
