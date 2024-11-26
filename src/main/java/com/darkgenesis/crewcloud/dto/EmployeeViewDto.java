package com.darkgenesis.crewcloud.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EmployeeViewDto {

    private Long employeeId;
    private Long nic;
    private String firstName;
    private String lastName;
    private String role;
    private BigDecimal basicSalary;
    private String designation;

    private String departmentName;
    
}
