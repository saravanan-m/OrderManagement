package com.order.management.controllers;

import com.order.management.dtos.ActiveDockDto;
import com.order.management.dtos.DockDto;
import com.order.management.dtos.PODto;
import com.order.management.services.DockService;
import com.order.management.services.POService;
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
@RequestMapping(value = "/api/v1/po")
public class POController {

    @Autowired
    POService poService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<PODto> addDock(@Validated @RequestBody PODto poDto) {
        return new ResponseEntity<>(poService.addPO(poDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<PODto>> getDocks() {
        return new ResponseEntity<>(poService.getPOs(), HttpStatus.OK);
    }

   /* @RequestMapping(value = "/activate-dock", method = RequestMethod.POST)
    public ResponseEntity<ActiveDockDto> addDock(@Validated @RequestBody ActiveDockDto activeDockDto) {

        activeDockDto = dockService.activateDock(activeDockDto);
        if (activeDockDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dock not available or conflicting time slot");
        }
        return new ResponseEntity<>(activeDockDto, HttpStatus.OK);
    }*/
}
