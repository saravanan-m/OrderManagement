package com.order.management.mappers;

import com.order.management.dtos.DockDto;
import com.order.management.entities.DockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface DockMapper {
    DockMapper INSTANCE = Mappers.getMapper(DockMapper.class);

    DockEntity toEntity(DockDto dockDto);
    DockDto toDto(DockEntity dockEntity);
}
