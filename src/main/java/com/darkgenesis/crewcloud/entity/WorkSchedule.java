package com.darkgenesis.crewcloud.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class WorkSchedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    private LocalDate date;
    private Boolean isWorkingDay;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;

    public enum AttendanceStatus{
        PENDING,
        PRESENT,
        ABSENT
    }

    @PrePersist
    protected void onCreate(){
        if(this.attendanceStatus == null){
            this.attendanceStatus = AttendanceStatus.PENDING;
        }
    }
}
