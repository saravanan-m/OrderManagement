package com.order.management.mappers;

import com.order.management.dtos.PODto;
import com.order.management.entities.POEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface POMapper {
    POMapper INSTANCE = Mappers.getMapper(POMapper.class);

    POEntity toEntity(PODto poDto);

    PODto toDto(POEntity poEntity);
}
