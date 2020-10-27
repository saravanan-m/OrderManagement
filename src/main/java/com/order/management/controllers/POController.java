package com.order.management.controllers;

import com.order.management.dtos.*;
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

    @RequestMapping(value = "/schedule-po", method = RequestMethod.POST)
    public ResponseEntity<List<ResponseScheduleDto>> schedulePO(@Validated @RequestBody SchedulePODto poDto) {
        try {
            return new ResponseEntity<>(poService.schedulePO(poDto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<PODto> addDock(@Validated @RequestBody PODto poDto) {
        try {
            return new ResponseEntity<>(poService.addPO(poDto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<List<PODto>> getDocks() {
        return new ResponseEntity<>(poService.getPOs(), HttpStatus.OK);
    }

}
