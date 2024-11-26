package com.darkgenesis.crewcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.darkgenesis.crewcloud.dto.LeaveDto;
import com.darkgenesis.crewcloud.dto.LeaveViewDto;
import com.darkgenesis.crewcloud.entity.Leave;
import com.darkgenesis.crewcloud.entity.Leave.LeaveStatus;
import com.darkgenesis.crewcloud.service.LeaveService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/leave")
@CrossOrigin("*")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping
    public ResponseEntity<Leave> createLeave(@RequestBody LeaveDto leaveDto){
        
        Leave leave = leaveService.createLeave(leaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(leave);
    }

    @PatchMapping("/{leaveId}")
    public ResponseEntity<Leave> handleLeave(@PathVariable Long leaveId, @RequestParam LeaveStatus leaveStatus){

        Leave updateLeave = leaveService.handleleave(leaveId, leaveStatus);
        return ResponseEntity.status(HttpStatus.OK).body(updateLeave);
    }

    @GetMapping
    public ResponseEntity<List<LeaveViewDto>> viewAllLeave(){
        List<LeaveViewDto> leaves = leaveService.viewAllLeave();
        return ResponseEntity.status(HttpStatus.OK).body(leaves);
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<LeaveViewDto> viewLeaveById(@PathVariable Long leaveId){
        LeaveViewDto leave = leaveService.viewLeaveById(leaveId);
        return ResponseEntity.status(HttpStatus.OK).body(leave);
    }

    @PutMapping("/{leaveId}")
    public ResponseEntity<Leave> updateLeave(@PathVariable Long leaveId, @RequestBody LeaveDto leaveDto) {
        Leave updatedLeave = leaveService.updateLeave(leaveId, leaveDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedLeave);
    }

    @DeleteMapping("/{leaveId}")
    public void deleteLeave(@PathVariable Long leaveId){
        leaveService.deleteLeave(leaveId);
    }

    
    
    
    
    
}
