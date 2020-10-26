package com.order.management.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchedulePODto {

    @NotBlank(message = "date can't be empty")
    private String date;

    private List<PODockDto> poDockDtos;
}
