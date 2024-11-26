package com.darkgenesis.crewcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.darkgenesis.crewcloud.dto.EmployeeDto;
import com.darkgenesis.crewcloud.dto.EmployeeViewDto;
import com.darkgenesis.crewcloud.entity.Employee;
import com.darkgenesis.crewcloud.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee createdEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeViewDto>> viewAllEmployee(){

        List<EmployeeViewDto> employees = employeeService.viewAllEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeViewDto> viewEmployeeById(@PathVariable Long employeeId){

        EmployeeViewDto employee = employeeService.viewEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employeeDto){

        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
    
    
    
    

