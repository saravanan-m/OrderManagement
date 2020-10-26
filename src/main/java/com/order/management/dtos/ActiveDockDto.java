package com.order.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ActiveDockDto {
    private Long id;

    @JsonProperty(value = "input_date")
    @NotBlank(message = "Date cant be empty")
    private String inputDate;

    @JsonProperty(value = "dock_id")
    @Range(min = 1, message = "dock id cant be empty")
    private long dockId;

    private String date;

    private int startTime;

    private int endTime;

    public void populateData() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = dateFormat.parse(inputDate);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");


            this.date = dateFormat2.format(date);
            ;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int startTime = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
            int oneHour = 60 * 60;

            this.startTime = startTime;
            this.endTime = startTime + oneHour;

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
