package com.order.management.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DockDto {

    private Long id;

    @NotBlank(message = "Dock name cant be null")
    private String name;

    @Range(min = 1, message = "Dock capacity cant be null")
    private int capacity;
}
