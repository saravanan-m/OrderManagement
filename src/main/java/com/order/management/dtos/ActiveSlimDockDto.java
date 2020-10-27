package com.order.management.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveSlimDockDto {
    private Long id;

    @JsonProperty(value = "slot_start_dt")
    private String startTime;

    @JsonProperty(value = "slot_end_dt")
    private String endTime;

    @JsonProperty(value = "dock_id")
    private long dockId;
}
