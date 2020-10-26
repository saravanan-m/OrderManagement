package com.order.management.services.impl;


import com.order.management.dtos.DockDto;
import com.order.management.entities.DockEntity;
import com.order.management.mappers.DockMapper;
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
}
