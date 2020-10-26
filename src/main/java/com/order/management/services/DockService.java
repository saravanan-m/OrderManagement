package com.order.management.services;


import com.order.management.dtos.DockDto;

import java.util.List;

public interface DockService {
    DockDto addDock(DockDto dockDto);

    List<DockDto> getDocks();


}
