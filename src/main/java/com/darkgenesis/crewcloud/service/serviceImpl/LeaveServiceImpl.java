package com.darkgenesis.crewcloud.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.dto.LeaveDto;
import com.darkgenesis.crewcloud.dto.LeaveViewDto;
import com.darkgenesis.crewcloud.entity.Employee;
import com.darkgenesis.crewcloud.entity.Leave;
import com.darkgenesis.crewcloud.entity.Leave.LeaveStatus;
import com.darkgenesis.crewcloud.entity.WorkSchedule;
import com.darkgenesis.crewcloud.repository.EmployeeRepo;
import com.darkgenesis.crewcloud.repository.LeaveRepo;
import com.darkgenesis.crewcloud.repository.WorkScheduleRepo;
import com.darkgenesis.crewcloud.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{
    
    @Autowired
    private LeaveRepo leaveRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private WorkScheduleRepo workScheduleRepo;

    @Override
    public Leave createLeave(LeaveDto leaveDto) {
        
        Employee employee = employeeRepo.findById(leaveDto.getEmployeeId()).orElse(null);

        if(leaveDto.getEndDate().isBefore(leaveDto.getStartDate())){
            throw new IllegalArgumentException("End Date cannot be before Start Date");
        }

        Leave leave = new Leave();
        leave.setEmployee(employee);
        leave.setStartDate(leaveDto.getStartDate());
        leave.setEndDate(leaveDto.getEndDate());
        leave.setLeaveReason(leaveDto.getLeaveReason());
        leave.setLeaveStatus(LeaveStatus.PENDING);

        return leaveRepo.save(leave);
    }

    @Override
    public Leave handleleave(Long leaveId, LeaveStatus leaveStatus) {
        
        Leave leave = leaveRepo.findById(leaveId).orElse(null);

        leave.setLeaveStatus(leaveStatus);

        if(leaveStatus == LeaveStatus.APPROVED){

            Employee employee = employeeRepo.findById(leave.getEmployee().getEmployeeId()).orElse(null);

            LocalDate start = leave.getStartDate();
            LocalDate end = leave.getEndDate();

            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)){
                WorkSchedule workSchedule = workScheduleRepo.findByEmployeeAndDate(employee, date);

                workSchedule.setIsWorkingDay(false);
                workScheduleRepo.save(workSchedule);
            }
        }
        return leaveRepo.save(leave);

    }

    @Override
    public List<LeaveViewDto> viewAllLeave() {
        List<Leave> leaves = leaveRepo.findAll();

        List<LeaveViewDto> leaveList = leaves.stream().map(leave ->{

            LeaveViewDto leaveViewDto = new LeaveViewDto();
            leaveViewDto.setLeaveId(leave.getLeaveId());
            leaveViewDto.setStartDate(leave.getStartDate());
            leaveViewDto.setEndDate(leave.getEndDate());
            leaveViewDto.setLeaveReason(leave.getLeaveReason());
            leaveViewDto.setLeaveStatus(leave.getLeaveStatus());
            leaveViewDto.setEmployeeName(leave.getEmployee().getFirstName());

            return leaveViewDto;
            
        }).collect(Collectors.toList());

        return leaveList;
    }

    @Override
    public LeaveViewDto viewLeaveById(Long leaveId) {

        Leave leave = leaveRepo.findById(leaveId).orElse(null);
        
        LeaveViewDto leaveViewDto = new LeaveViewDto();
            leaveViewDto.setLeaveId(leave.getLeaveId());
            leaveViewDto.setStartDate(leave.getStartDate());
            leaveViewDto.setEndDate(leave.getEndDate());
            leaveViewDto.setLeaveReason(leave.getLeaveReason());
            leaveViewDto.setLeaveStatus(leave.getLeaveStatus());
            leaveViewDto.setEmployeeName(leave.getEmployee().getFirstName());

            return leaveViewDto;
    }

    @Override
    public Leave updateLeave(Long leaveId, LeaveDto leaveDto) {
        
        Leave existingLeave = leaveRepo.findById(leaveId).orElse(null);

        String leaveStatus = leaveDto.getLeaveStatus();

        existingLeave.setStartDate(leaveDto.getStartDate());
        existingLeave.setEndDate(leaveDto.getEndDate());
        existingLeave.setLeaveReason(leaveDto.getLeaveReason());

        if("PENDING".equalsIgnoreCase(leaveStatus)){
            existingLeave.setLeaveStatus(LeaveStatus.PENDING);
        }else if("APPROVED".equalsIgnoreCase(leaveStatus)){
            existingLeave.setLeaveStatus(LeaveStatus.APPROVED);
        }else if("REJECTED".equalsIgnoreCase(leaveStatus)){
            existingLeave.setLeaveStatus(LeaveStatus.REJECTED);
        }
        return leaveRepo.save(existingLeave);
    }

    @Override
    public void deleteLeave(Long leaveId) {

        leaveRepo.deleteById(leaveId);
    }

}
