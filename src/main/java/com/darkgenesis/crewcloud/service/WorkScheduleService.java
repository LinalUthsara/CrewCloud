package com.darkgenesis.crewcloud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.dto.WorkScheduleDto;
import com.darkgenesis.crewcloud.dto.WorkScheduleUpdateDto;
import com.darkgenesis.crewcloud.dto.WorkScheduleViewDto;
import com.darkgenesis.crewcloud.entity.WorkSchedule;
import com.darkgenesis.crewcloud.entity.WorkSchedule.AttendanceStatus;

@Service
public interface WorkScheduleService {

    List<WorkSchedule> createWorkSchedule(WorkScheduleDto workScheduleDto);
    WorkSchedule viewScheduleByEmployeeIdAndDate(Long employeeId);
    WorkSchedule markScheduleAttendance(Long scheduleId, AttendanceStatus attendanceStatus);
    List<WorkScheduleViewDto> viewAllSchedule();
    List<WorkScheduleViewDto> viewAllScheduleByEmployeeId(Long employeeId);
    WorkScheduleViewDto viewScheduleById(Long scheduleId);
    WorkSchedule updateSchedule(Long scheduleId, WorkScheduleUpdateDto workScheduleUpdateDto);
    void deleteSchedule(Long scheduleId);
}
