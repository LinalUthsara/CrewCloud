package com.darkgenesis.crewcloud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.dto.SalaryDto;
import com.darkgenesis.crewcloud.dto.SalaryUpdateDto;
import com.darkgenesis.crewcloud.dto.SalaryViewDto;
import com.darkgenesis.crewcloud.entity.Salary;
import com.darkgenesis.crewcloud.entity.Salary.SalaryStatus;

@Service
public interface SalaryService {

    Salary createSalary(SalaryDto salaryDto);
    Salary handleSalary(Long salaryId, SalaryStatus salaryStatus);
    
    List<SalaryViewDto> viewAllSalary();
    SalaryViewDto viewSalaryById(Long salaryId);
    Salary updateSalary(Long salaryId, SalaryUpdateDto salaryUpdateDto);
    void deleteSalary(Long salaryId);
}
