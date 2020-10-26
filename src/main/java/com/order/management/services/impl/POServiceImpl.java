package com.order.management.services.impl;


import com.order.management.dtos.ActiveDockDto;
import com.order.management.dtos.DockDto;
import com.order.management.dtos.PODockDto;
import com.order.management.dtos.PODto;
import com.order.management.entities.ActiveDockEntity;
import com.order.management.entities.DockEntity;
import com.order.management.entities.POEntity;
import com.order.management.mappers.ActiveDockMapper;
import com.order.management.mappers.DockMapper;
import com.order.management.mappers.POMapper;
import com.order.management.repositories.ActiveDockRepository;
import com.order.management.repositories.DockRepository;
import com.order.management.repositories.PORepository;
import com.order.management.services.DockService;
import com.order.management.services.POService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class POServiceImpl implements POService {

    @Autowired
    PORepository poRepository;

    @Override
    public PODto addPO(PODto poDto) {
        POEntity entity = POMapper.INSTANCE.toEntity(poDto);
        entity = poRepository.save(entity);
        return POMapper.INSTANCE.toDto(entity);
    }

    @Override
    public List<PODto> getPOs() {
        return Optional.ofNullable(poRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(POMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
