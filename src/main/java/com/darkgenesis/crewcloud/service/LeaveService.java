package com.darkgenesis.crewcloud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.dto.LeaveDto;
import com.darkgenesis.crewcloud.dto.LeaveViewDto;
import com.darkgenesis.crewcloud.entity.Leave;
import com.darkgenesis.crewcloud.entity.Leave.LeaveStatus;

@Service
public interface LeaveService {
    
    Leave createLeave(LeaveDto leaveDto);
    Leave handleleave(Long leaveId, LeaveStatus leaveStatus);

    List<LeaveViewDto> viewAllLeave();
    LeaveViewDto viewLeaveById(Long leaveId);
    public Leave updateLeave(Long leaveId, LeaveDto leaveDto);
    public void deleteLeave(Long leaveId);
}
