package com.order.management.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ParsedDock {
    private int sumCapacity;

    private int Capacity;

    private List<ActiveDockDto> activeDockDtos = new ArrayList<>();
}
