package com.order.management.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseScheduleDto {
    @JsonProperty(value = "po")
    private PODto poDto;

    @JsonProperty(value = "scheduled_dock")
    private List<ActiveSlimDockDto> activeDockDtos = new ArrayList<>();
}
