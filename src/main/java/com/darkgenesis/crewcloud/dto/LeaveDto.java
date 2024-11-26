package com.darkgenesis.crewcloud.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LeaveDto {
    
    private long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveReason;
    private String leaveStatus;


}
