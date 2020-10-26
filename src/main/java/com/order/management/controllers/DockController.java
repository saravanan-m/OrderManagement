package com.order.management.controllers;

import com.order.management.dtos.DockDto;
import com.order.management.services.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<DockDto>> getDocks() {
        return new ResponseEntity<>(dockService.getDocks(), HttpStatus.OK);
    }
}
