package com.order.management.services;


import com.order.management.dtos.PODto;
import com.order.management.dtos.ResponseScheduleDto;
import com.order.management.dtos.SchedulePODto;

import java.util.List;

public interface POService {
    PODto addPO(PODto poDto);

    List<PODto> getPOs();

    List<ResponseScheduleDto>  schedulePO(SchedulePODto schedulePODto);
}
