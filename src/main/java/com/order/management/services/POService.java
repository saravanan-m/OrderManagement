package com.order.management.services;


import com.order.management.dtos.PODto;

import java.util.List;

public interface POService {
    PODto addPO(PODto poDto);

    List<PODto> getPOs();
}
