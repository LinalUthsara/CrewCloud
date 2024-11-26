package com.darkgenesis.crewcloud.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EmployeeDto {
    
    private Long nic;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private BigDecimal basicSalary;
    private String designation;

    private Long departmentId;
}
