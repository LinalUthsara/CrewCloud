package com.darkgenesis.crewcloud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.darkgenesis.crewcloud.entity.Salary.SalaryStatus;

import lombok.Data;

@Data
public class SalaryViewDto {

    private Long salaryId;
    private String EmployeeName;
    private BigDecimal basicSalary;
    private BigDecimal overTimeSalary;
    private BigDecimal allowances;
    private BigDecimal deductions;
    private BigDecimal netSalary;
    private LocalDate payDate;
    private SalaryStatus salaryStatus;
    
}
