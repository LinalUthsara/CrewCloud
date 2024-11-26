package com.darkgenesis.crewcloud.dto;

import java.time.LocalDate;

import com.darkgenesis.crewcloud.entity.Leave.LeaveStatus;

import lombok.Data;

@Data
public class LeaveViewDto {

    private Long leaveId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveReason;
    private LeaveStatus leaveStatus;
    
}
