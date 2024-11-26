package com.darkgenesis.crewcloud.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darkgenesis.crewcloud.dto.WorkScheduleDto;
import com.darkgenesis.crewcloud.dto.WorkScheduleUpdateDto;
import com.darkgenesis.crewcloud.dto.WorkScheduleViewDto;
import com.darkgenesis.crewcloud.entity.Employee;
import com.darkgenesis.crewcloud.entity.WorkSchedule;
import com.darkgenesis.crewcloud.entity.WorkSchedule.AttendanceStatus;
import com.darkgenesis.crewcloud.exception.WorkScheduleNotFoundException;
import com.darkgenesis.crewcloud.repository.EmployeeRepo;
import com.darkgenesis.crewcloud.repository.WorkScheduleRepo;
import com.darkgenesis.crewcloud.service.WorkScheduleService;

@Service
public class WorkScheduleServiceImpl implements WorkScheduleService{
    
    @Autowired
    private WorkScheduleRepo workScheduleRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    @Transactional
    @Override
    public List<WorkSchedule> createWorkSchedule(WorkScheduleDto workScheduleDto){

        Employee employee = employeeRepo.findById(workScheduleDto.getEmployeeId()).orElse(null);

        List<WorkSchedule> schedules = workScheduleDto.getWorkDays().stream().map(workday ->{
            WorkSchedule workSchedule = new WorkSchedule();
            workSchedule.setEmployee(employee);
            workSchedule.setDate(workday.getDate());
            workSchedule.setIsWorkingDay(workday.getIsWorkingDay());
            return workSchedule;
        })
        .collect(Collectors.toList());

        return workScheduleRepo.saveAll(schedules);
    }


    @Override
    public WorkSchedule viewScheduleByEmployeeIdAndDate(Long employeeId) {

        Employee employee = employeeRepo.findById(employeeId).orElse(null);
        return workScheduleRepo.findByEmployeeAndDate(employee, LocalDate.now());
    }


    @Override
    public WorkSchedule markScheduleAttendance(Long scheduleId, AttendanceStatus attendanceStatus) {
        
        WorkSchedule workSchedule = workScheduleRepo.findById(scheduleId).orElse(null);

        Boolean workingDay = workSchedule.getIsWorkingDay();
        
        if(workingDay == true){

            workSchedule.setAttendanceStatus(attendanceStatus);

        }else{
            return null;
        }
         
        return workScheduleRepo.save(workSchedule);
    }


    @Override
    public List<WorkScheduleViewDto> viewAllSchedule() {
        
        List<WorkSchedule> schedules = workScheduleRepo.findAll();

        List<WorkScheduleViewDto> scheduleList = schedules.stream().map(schedule ->{

            Boolean isWorkingDay = schedule.getIsWorkingDay();
            String attendanceStatus = String.valueOf(schedule.getAttendanceStatus());

            WorkScheduleViewDto workScheduleViewDto = new WorkScheduleViewDto();
            workScheduleViewDto.setScheduleId(schedule.getScheduleId());
            workScheduleViewDto.setDate(schedule.getDate());
            workScheduleViewDto.setEmployeeName(schedule.getEmployee().getFirstName());

            if(isWorkingDay == true){
                workScheduleViewDto.setWorkStatus("WorkDay");
            } else if (isWorkingDay == false){
                workScheduleViewDto.setWorkStatus("Holiday");
            } else {
                workScheduleViewDto.setWorkStatus("Unknown");
            }

            workScheduleViewDto.setAttendanceStatus(attendanceStatus);

            return workScheduleViewDto;
        }).collect(Collectors.toList());

        return scheduleList;
    }


    @Override
    public List<WorkScheduleViewDto> viewAllScheduleByEmployeeId(Long employeeId) {

        Employee employee = employeeRepo.findById(employeeId).orElse(null);
        
        List<WorkSchedule> schedules = workScheduleRepo.findByEmployee(employee);

        List<WorkScheduleViewDto> scheduleList = schedules.stream().map(schedule ->{

            Boolean isWorkingDay = schedule.getIsWorkingDay();
            String attendanceStatus = String.valueOf(schedule.getAttendanceStatus());

            WorkScheduleViewDto workScheduleViewDto = new WorkScheduleViewDto();
            workScheduleViewDto.setScheduleId(schedule.getScheduleId());
            workScheduleViewDto.setDate(schedule.getDate());
            workScheduleViewDto.setEmployeeName(schedule.getEmployee().getFirstName());

            if(isWorkingDay == true){
                workScheduleViewDto.setWorkStatus("WorkDay");
            } else if (isWorkingDay == false){
                workScheduleViewDto.setWorkStatus("Holiday");
            } else {
                workScheduleViewDto.setWorkStatus("Unknown");
            }

            workScheduleViewDto.setAttendanceStatus(attendanceStatus);

            return workScheduleViewDto;
        }).collect(Collectors.toList());

        return scheduleList;


    }


    @Override
    public WorkScheduleViewDto viewScheduleById(Long scheduleId) {
        
        WorkSchedule schedule = workScheduleRepo.findById(scheduleId).orElse(null);

        Boolean isWorkingDay = schedule.getIsWorkingDay();
        String attendanceStatus = String.valueOf(schedule.getAttendanceStatus());

        WorkScheduleViewDto workScheduleViewDto = new WorkScheduleViewDto();
        workScheduleViewDto.setScheduleId(scheduleId);
        workScheduleViewDto.setDate(schedule.getDate());
        workScheduleViewDto.setEmployeeName(schedule.getEmployee().getFirstName());

        if(isWorkingDay == true){
            workScheduleViewDto.setWorkStatus("WorkDay");
        } else if (isWorkingDay == false){
                workScheduleViewDto.setWorkStatus("Holiday");
        } else {
            workScheduleViewDto.setWorkStatus("Unknown");
        }

        workScheduleViewDto.setAttendanceStatus(attendanceStatus);

        return workScheduleViewDto;
    }


    @Override
    public WorkSchedule updateSchedule(Long scheduleId, WorkScheduleUpdateDto workScheduleUpdateDto) {
        
        WorkSchedule workSchedule = workScheduleRepo.findById(scheduleId).orElseThrow(() ->new WorkScheduleNotFoundException("Work Schedule not found"));
        
        if(workScheduleUpdateDto.getAttendanceStatus().equals("ABSENT")){
            workSchedule.setAttendanceStatus(AttendanceStatus.ABSENT);
        } else if(workScheduleUpdateDto.getAttendanceStatus().equals("PRESENT")){
            workSchedule.setAttendanceStatus(AttendanceStatus.PRESENT);
        } else{
            workSchedule.setAttendanceStatus(AttendanceStatus.PENDING);
        }


        if(workScheduleUpdateDto.getWorkStatus().equals("WorkDay")){
            workSchedule.setIsWorkingDay(true);
        }else{
            workSchedule.setIsWorkingDay(false);
        }

        return workScheduleRepo.save(workSchedule);
    }
    
    @Override
    public void deleteSchedule(Long scheduleId) {
       
        workScheduleRepo.deleteById(scheduleId);
    }
}
