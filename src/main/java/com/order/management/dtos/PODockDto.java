package com.order.management.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PODockDto {

    private Long id;

    private ActiveDockDto activeDock;

    private PODockDto po;
}
