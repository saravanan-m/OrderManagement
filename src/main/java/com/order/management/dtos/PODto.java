package com.order.management.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PODto {

    private Long id;

    @JsonProperty(value = "item_id")
    @Range(min = 1, message = "item id cant be null")
    private int itemId;

    @Range(min = 1, message = "quantity cant be null")
    private int quantity;
}
