package com.order.management.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DockDto {

    private Long id;

    @NotBlank(message = "Dock name cant be null")
    private String name;

    @Range(min = 1, message = "Dock capacity cant be null")
    private int capacity;

    private Set<ActiveDockDto> activeDocks;
}
