package com.darkgenesis.crewcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.darkgenesis.crewcloud.dto.WorkScheduleDto;
import com.darkgenesis.crewcloud.dto.WorkScheduleUpdateDto;
import com.darkgenesis.crewcloud.dto.WorkScheduleViewDto;
import com.darkgenesis.crewcloud.entity.WorkSchedule;
import com.darkgenesis.crewcloud.entity.WorkSchedule.AttendanceStatus;
import com.darkgenesis.crewcloud.service.WorkScheduleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/workSchedule")
@CrossOrigin("*")
public class WorkScheduleController {
    
    @Autowired
    private WorkScheduleService workScheduleService;

    @PostMapping
    public ResponseEntity<List<WorkSchedule>> createSchedule(@RequestBody WorkScheduleDto workScheduleDto) {
        List<WorkSchedule> schedules = workScheduleService.createWorkSchedule(workScheduleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedules);
    }

    @GetMapping("employee/{employeeId}")
    public ResponseEntity<WorkSchedule> viewScheduleByEmployeeIdAndDate(@PathVariable Long employeeId) {
        WorkSchedule workSchedule = workScheduleService.viewScheduleByEmployeeIdAndDate(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(workSchedule);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<WorkSchedule> markScheduleAttendance (@PathVariable Long scheduleId, @RequestParam AttendanceStatus attendanceStatus){

        WorkSchedule updatedWorkSchedule = workScheduleService.markScheduleAttendance(scheduleId, attendanceStatus);

        return ResponseEntity.status(HttpStatus.OK).body(updatedWorkSchedule);
    }

    @GetMapping
    public ResponseEntity<List<WorkScheduleViewDto>> viewAllSchedule(){
        List<WorkScheduleViewDto> schedules = workScheduleService.viewAllSchedule();
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<WorkScheduleViewDto> viewScheduleById(@PathVariable Long scheduleId){
        WorkScheduleViewDto schedule = workScheduleService.viewScheduleById(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<WorkSchedule> updateSchedule(@PathVariable Long scheduleId, @RequestBody WorkScheduleUpdateDto workScheduleUpdateDto){
        WorkSchedule updateSchedule = workScheduleService.updateSchedule(scheduleId, workScheduleUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateSchedule);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId){

        workScheduleService.deleteSchedule(scheduleId);
    }
}
