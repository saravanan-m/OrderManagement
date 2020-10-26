package com.order.management.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ActiveDockDto {
    private Long id;

    @NotBlank(message = "Date cant be empty")
    private String inputDate;

    @Range(min = 1,message = "dock id cant be empty")
    private int dockId;

    private String date;

    private int startTime;

    private int endTime;
}
