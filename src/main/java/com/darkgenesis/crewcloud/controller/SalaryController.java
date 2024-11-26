package com.darkgenesis.crewcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darkgenesis.crewcloud.dto.SalaryDto;
import com.darkgenesis.crewcloud.dto.SalaryUpdateDto;
import com.darkgenesis.crewcloud.dto.SalaryViewDto;
import com.darkgenesis.crewcloud.entity.Salary.SalaryStatus;
import com.darkgenesis.crewcloud.entity.Salary;
import com.darkgenesis.crewcloud.service.SalaryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/salary")
@CrossOrigin("*")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody SalaryDto salaryDto) {
        
        Salary salary = salaryService.createSalary(salaryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salary);
    }
    
    @PatchMapping("/{salaryId}")
    public ResponseEntity<Salary> handleSalary(@PathVariable Long salaryId, @RequestParam SalaryStatus salaryStatus){

        Salary updateSalary = salaryService.handleSalary(salaryId, salaryStatus);
        return ResponseEntity.status(HttpStatus.OK).body(updateSalary);
    } 

    @GetMapping
    public ResponseEntity<List<SalaryViewDto>> viewAllSalary(){
        List<SalaryViewDto> salaries = salaryService.viewAllSalary();
        return ResponseEntity.status(HttpStatus.OK).body(salaries);
    }

    @PutMapping("/{salaryId}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long salaryId, @RequestBody SalaryUpdateDto salaryUpdateDto){

        Salary updatedSalary = salaryService.updateSalary(salaryId, salaryUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSalary);
    }

    @DeleteMapping("/{salaryId}")
    public void deleteSalary(@PathVariable Long salaryId){
        salaryService.deleteSalary(salaryId);
    }

    @GetMapping("/{salaryId}")
    public ResponseEntity<SalaryViewDto> viewSalaryById(@PathVariable Long salaryId){
        SalaryViewDto salary = salaryService.viewSalaryById(salaryId);
        return ResponseEntity.status(HttpStatus.OK).body(salary);
    }
      
}
