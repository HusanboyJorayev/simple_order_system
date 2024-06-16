package org.example.simple_order_sytem.mapper;

import org.example.simple_order_sytem.dto.OfficeDto;
import org.example.simple_order_sytem.entity.Office;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OfficeMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    Office toOffice(OfficeDto dto);

    OfficeDto toDto(Office entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(OfficeDto dto, @MappingTarget Office entity);
}
