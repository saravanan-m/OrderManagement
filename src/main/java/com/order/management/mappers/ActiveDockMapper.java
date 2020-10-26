package com.order.management.mappers;

import com.order.management.dtos.ActiveDockDto;
import com.order.management.dtos.DockDto;
import com.order.management.entities.ActiveDockEntity;
import com.order.management.entities.DockEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public abstract class ActiveDockMapper {
    ActiveDockMapper INSTANCE = Mappers.getMapper(ActiveDockMapper.class);

    abstract ActiveDockEntity toEntity(ActiveDockDto activeDockDto);

    abstract ActiveDockDto toDto(ActiveDockEntity activeDockEntity);

    @AfterMapping
    public void dtoToEntity(@MappingTarget ActiveDockEntity entity,ActiveDockDto dto){

    }
}
