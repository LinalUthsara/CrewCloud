package com.darkgenesis.crewcloud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.dto.EmployeeDto;
import com.darkgenesis.crewcloud.dto.EmployeeViewDto;
import com.darkgenesis.crewcloud.entity.Employee;

@Service
public interface EmployeeService {
    
    Employee createEmployee(EmployeeDto employeeDto);
    List<EmployeeViewDto> viewAllEmployee();
    EmployeeViewDto viewEmployeeById(Long employeeId);
    Employee updateEmployee(Long employeeId, EmployeeDto employeeDto);
    void deleteEmployee(Long employeeId);

}
