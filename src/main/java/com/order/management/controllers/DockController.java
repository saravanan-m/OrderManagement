package com.order.management.controllers;

import com.order.management.dtos.ActiveDockDto;
import com.order.management.dtos.DockDto;
import com.order.management.services.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/dock")
public class DockController {

    @Autowired
    DockService dockService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<DockDto> addDock(@Validated @RequestBody DockDto dockDto) {
        return new ResponseEntity<>(dockService.addDock(dockDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<DockDto>> getDocks(@RequestParam(value = "date" ,required = false) String date) {
        String parsedDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date lDate = dateFormat.parse(date);
            parsedDate = dateFormat.format(lDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(parsedDate == null ? dockService.getDocks() : dockService.getActiveDocks(parsedDate), HttpStatus.OK);
    }

    @RequestMapping(value = "/activate-dock", method = RequestMethod.POST)
    public ResponseEntity<ActiveDockDto> addDock(@Validated @RequestBody ActiveDockDto activeDockDto) {

        activeDockDto = dockService.activateDock(activeDockDto);
        if (activeDockDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dock not available or conflicting time slot");
        }
        return new ResponseEntity<>(activeDockDto, HttpStatus.OK);
    }
}
