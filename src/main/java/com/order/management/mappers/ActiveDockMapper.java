package com.order.management.mappers;

import com.order.management.dtos.ActiveDockDto;
import com.order.management.dtos.DockDto;
import com.order.management.entities.ActiveDockEntity;
import com.order.management.entities.DockEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Mapper
public abstract class ActiveDockMapper {
    public static ActiveDockMapper INSTANCE = Mappers.getMapper(ActiveDockMapper.class);

    public abstract ActiveDockEntity toEntity(ActiveDockDto activeDockDto);

    @Mappings({
            @Mapping(source = "dockEntity.id", target = "dockId")
    })
    public abstract ActiveDockDto toDto(ActiveDockEntity activeDockEntity);

    @AfterMapping
    public void dtoToEntity(@MappingTarget ActiveDockEntity entity, ActiveDockDto dto) {

    }
}
