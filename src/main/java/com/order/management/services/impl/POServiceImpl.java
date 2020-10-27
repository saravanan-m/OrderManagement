package com.order.management.services.impl;


import com.order.management.dtos.*;
import com.order.management.entities.ActiveDockEntity;
import com.order.management.entities.DockEntity;
import com.order.management.entities.PODockEntity;
import com.order.management.entities.POEntity;
import com.order.management.mappers.ActiveDockMapper;
import com.order.management.mappers.DockMapper;
import com.order.management.mappers.POMapper;
import com.order.management.repositories.ActiveDockRepository;
import com.order.management.repositories.DockRepository;
import com.order.management.repositories.PODockRepository;
import com.order.management.repositories.PORepository;
import com.order.management.services.DockService;
import com.order.management.services.POService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class POServiceImpl implements POService {

    @Autowired
    PORepository poRepository;

    @Autowired
    DockRepository dockRepository;

    @Autowired
    PODockRepository poDockRepository;

    @Autowired
    ActiveDockRepository activeDockRepository;

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

    @Override
    public List<ResponseScheduleDto>  schedulePO(SchedulePODto schedulePODto) {
        String parsedDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date lDate = dateFormat.parse(schedulePODto.getDate());
            parsedDate = dateFormat.format(lDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (parsedDate == null) {
            throw new RuntimeException("Illegal date format");
        }

        ArrayList<ParsedDock> parsedDocks = new ArrayList<>();
        List<DockEntity> activeDockEntities = dockRepository.findByDateDistinct(parsedDate);
        for (DockEntity dockEntity : activeDockEntities) {

            int endTime = -1;
            ParsedDock parsedDock = null;

            ArrayList<ActiveDockEntity> list = new ArrayList<>(dockEntity.getActiveDocks());
            Collections.sort(list, activeComparator);
            for (ActiveDockEntity activeDockEntity : list) {
                if (endTime == activeDockEntity.getStartTime()) {

                    parsedDock.setSumCapacity(parsedDock.getSumCapacity() + dockEntity.getCapacity());
                    parsedDock.getActiveDockDtos().add(ActiveDockMapper.INSTANCE.toDto(activeDockEntity));

                    endTime = activeDockEntity.getEndTime();
                } else {
                    parsedDock = new ParsedDock();
                    parsedDock.setSumCapacity(dockEntity.getCapacity());
                    parsedDock.setCapacity(dockEntity.getCapacity());

                    parsedDock.getActiveDockDtos().add(ActiveDockMapper.INSTANCE.toDto(activeDockEntity));
                    parsedDocks.add(parsedDock);

                    endTime = activeDockEntity.getEndTime();
                }
            }

        }
        Collections.sort(parsedDocks, dockComparator);

        List<ResponseScheduleDto> responseScheduleDtos = new ArrayList<>();
        for (PODto poDto : schedulePODto.getPoDtos()) {

            if (parsedDocks.size() > 0) {
                ParsedDock parsedDock = parsedDocks.get(0);

                int reqQuantity = poDto.getQuantity();
                int allCapacity = parsedDock.getSumCapacity();
                if (reqQuantity <= allCapacity) {

                    POEntity poEntity = poRepository.save(POMapper.INSTANCE.toEntity(poDto));

                    ResponseScheduleDto responseScheduleDto = new ResponseScheduleDto();
                    responseScheduleDto.setPoDto(POMapper.INSTANCE.toDto(poEntity));
                    responseScheduleDtos.add(responseScheduleDto);

                    while (reqQuantity > 0 && parsedDock.getActiveDockDtos().size() > 0) {
                        ActiveDockDto activeDockDto = parsedDock.getActiveDockDtos().get(0);
                        reqQuantity = reqQuantity - parsedDock.getCapacity();
                        allCapacity = allCapacity - parsedDock.getCapacity();

                        parsedDock.getActiveDockDtos().remove(0);

                        PODockEntity poDockEntity = new PODockEntity();
                        poDockEntity.setPo(poEntity);
                        poDockEntity.setActiveDock(activeDockRepository.findById(activeDockDto.getId()).orElse(null));
                        poDockRepository.save(poDockEntity);

                        ActiveSlimDockDto slimDockDto = new ActiveSlimDockDto();
                        slimDockDto.setDockId(activeDockDto.getDockId());
                        slimDockDto.setStartTime(activeDockDto.getDate()+"'T'"+formatSeconds(activeDockDto.getStartTime()));
                        slimDockDto.setEndTime(activeDockDto.getDate()+"'T'"+formatSeconds(activeDockDto.getEndTime()));
                        responseScheduleDto.getActiveDockDtos().add(slimDockDto);
                    }

                    if(allCapacity == 0){
                        parsedDocks.remove(0);
                    }else{
                        parsedDock.setSumCapacity(allCapacity);
                    }
                }
            }
        }

        return responseScheduleDtos;
    }

    private String formatSeconds(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds;

        return formattedTime;
    }

    private Comparator<ActiveDockEntity> activeComparator = new Comparator<ActiveDockEntity>() {
        @Override
        public int compare(ActiveDockEntity t1, ActiveDockEntity t2) {

            return Integer.compare(t1.getStartTime(), t2.getStartTime());
        }
    };

    private Comparator<ParsedDock> dockComparator = new Comparator<ParsedDock>() {
        @Override
        public int compare(ParsedDock t1, ParsedDock t2) {

            return Integer.compare(t2.getCapacity(), t1.getCapacity());
        }
    };
}
