package com.darkgenesis.crewcloud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.darkgenesis.crewcloud.entity.Salary.SalaryStatus;

import lombok.Data;

@Data
public class SalaryUpdateDto {
    
    private BigDecimal allowances;
    private BigDecimal deductions;
    private SalaryStatus salaryStatus;
    private LocalDate payDate;
    
}
