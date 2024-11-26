package com.darkgenesis.crewcloud.entity;

import java.math.BigDecimal;
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
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    private BigDecimal overTimeSalary;
    private BigDecimal allowances;
    private BigDecimal deductions;

    private BigDecimal netSalary;

    private LocalDate payDate;

    @Enumerated(EnumType.STRING)
    private SalaryStatus salaryStatus;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Administrator approvedBy;

    public enum SalaryStatus{
        PENDING,
        APPROVED,
        REJECTED
    }

    @PrePersist
    protected void onCreate(){
        if(this.salaryStatus == null){
            this.salaryStatus = SalaryStatus.PENDING;
        }
    }
}

