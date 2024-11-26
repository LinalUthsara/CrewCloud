package com.darkgenesis.crewcloud.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class WorkScheduleDto {
    
    private Long employeeId;
    private List<WorkDay> workDays;


    @Data
    public static class WorkDay {

        private LocalDate date;
        private Boolean isWorkingDay;
        
    }
}
