package com.order.management.services.impl;


import com.order.management.dtos.ActiveDockDto;
import com.order.management.dtos.DockDto;
import com.order.management.entities.ActiveDockEntity;
import com.order.management.entities.DockEntity;
import com.order.management.mappers.ActiveDockMapper;
import com.order.management.mappers.DockMapper;
import com.order.management.repositories.ActiveDockRepository;
import com.order.management.repositories.DockRepository;
import com.order.management.services.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DockServiceImpl implements DockService {

    @Autowired
    DockRepository dockRepository;

    @Autowired
    ActiveDockRepository activeDockRepository;

    @Override
    public DockDto addDock(DockDto dockDto) {
        DockEntity entity = DockMapper.INSTANCE.toEntity(dockDto);
        entity = dockRepository.save(entity);
        return DockMapper.INSTANCE.toDto(entity);
    }

    @Override
    public List<DockDto> getDocks() {
        return Optional.ofNullable(dockRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(DockMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ActiveDockDto activateDock(ActiveDockDto activeDockDto) {
        activeDockDto.populateData();

        List<ActiveDockEntity> activeDockEntityList = activeDockRepository.findByDockIdAndDate(activeDockDto.getDockId(), activeDockDto.getDate());
        for (ActiveDockEntity entity : activeDockEntityList) {
            if (activeDockDto.getStartTime() >= entity.getStartTime() && activeDockDto.getStartTime() < entity.getEndTime()) {
                return null;
            }
        }

        DockEntity dockEntity = dockRepository.findById(activeDockDto.getDockId()).orElse(null);
        if (dockEntity == null) {
            return null;
        }

        ActiveDockEntity entity = ActiveDockMapper.INSTANCE.toEntity(activeDockDto);
        entity.setDockEntity(dockEntity);

        entity = activeDockRepository.save(entity);

        return ActiveDockMapper.INSTANCE.toDto(entity);
    }

    @Override
    public List<DockDto> getActiveDocks(String date) {
        return Optional.ofNullable(dockRepository.findByDateDistinct(date))
                .orElse(Collections.emptyList())
                .stream()
                .map(DockMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
