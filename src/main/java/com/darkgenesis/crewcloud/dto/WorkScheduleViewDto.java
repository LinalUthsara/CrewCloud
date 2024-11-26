package com.darkgenesis.crewcloud.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class WorkScheduleViewDto {
    
    private Long scheduleId;
    private LocalDate date;
    private String employeeName;
    private String workStatus;
    private String attendanceStatus;

}
