package com.darkgenesis.crewcloud.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SalaryDto {
    
    private Long employeeId;
    private BigDecimal allowances;
    private BigDecimal deductions;
}
