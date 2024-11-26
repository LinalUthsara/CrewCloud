package com.darkgenesis.crewcloud.service.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.dto.SalaryDto;
import com.darkgenesis.crewcloud.dto.SalaryUpdateDto;
import com.darkgenesis.crewcloud.dto.SalaryViewDto;
import com.darkgenesis.crewcloud.entity.Employee;
import com.darkgenesis.crewcloud.entity.Salary;
import com.darkgenesis.crewcloud.entity.Salary.SalaryStatus;
import com.darkgenesis.crewcloud.entity.WorkSchedule;
import com.darkgenesis.crewcloud.entity.WorkSchedule.AttendanceStatus;
import com.darkgenesis.crewcloud.repository.EmployeeRepo;
import com.darkgenesis.crewcloud.repository.SalaryRepo;
import com.darkgenesis.crewcloud.repository.WorkScheduleRepo;
import com.darkgenesis.crewcloud.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService{
    
    @Autowired
    private SalaryRepo salaryRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private WorkScheduleRepo workScheduleRepo;

    @Override
    public Salary createSalary(SalaryDto salaryDto) {
        
        Employee employee = employeeRepo.findById(salaryDto.getEmployeeId()).orElse(null);

        BigDecimal basicSalary = employee.getBasicSalary();
        BigDecimal allowances = salaryDto.getAllowances();
        BigDecimal deductions = salaryDto.getDeductions();

        YearMonth currentMonth = YearMonth.now();
        LocalDate startDate = currentMonth.atDay(1);
        LocalDate endDate = currentMonth.atEndOfMonth();


        List<WorkSchedule> attendanceList = workScheduleRepo.findAllByEmployeeAndDateBetween(employee, startDate, endDate);

        Long presentDays = attendanceList.stream().filter(a ->a.getAttendanceStatus() == AttendanceStatus.PRESENT).count();

        BigDecimal overTimeSalary = BigDecimal.ZERO;
        BigDecimal netSalary;

        if(presentDays < 22){
            netSalary = allowances.subtract(deductions);

        }else{

            Long overTimeDays = presentDays - 22;
            
            overTimeSalary = BigDecimal.valueOf(overTimeDays).multiply(BigDecimal.valueOf(1200));

            netSalary = basicSalary.add(overTimeSalary).add(allowances).subtract(deductions);
        }

        Salary salary = new Salary();
        salary.setEmployee(employee);
        salary.setOverTimeSalary(overTimeSalary);
        salary.setAllowances(allowances);
        salary.setDeductions(deductions);
        salary.setNetSalary(netSalary);
        
        return salaryRepo.save(salary);
    }

    @Override
    public Salary handleSalary(Long salaryId, SalaryStatus salaryStatus) {
        
        Salary salary = salaryRepo.findById(salaryId).orElse(null);

        salary.setSalaryStatus(salaryStatus);

        if(salaryStatus == SalaryStatus.APPROVED){

            salary.setPayDate(LocalDate.now());
        }
        return salaryRepo.save(salary);
    }

    @Override
    public List<SalaryViewDto> viewAllSalary() {
        List<Salary> salaries = salaryRepo.findAll();

        List<SalaryViewDto> salaryList = salaries.stream().map(salary ->{

            SalaryViewDto salaryViewDto = new SalaryViewDto();
            salaryViewDto.setSalaryId(salary.getSalaryId());
            salaryViewDto.setEmployeeName(salary.getEmployee().getFirstName());
            salaryViewDto.setBasicSalary(salary.getEmployee().getBasicSalary());
            salaryViewDto.setAllowances(salary.getAllowances());
            salaryViewDto.setDeductions(salary.getDeductions());
            salaryViewDto.setNetSalary(salary.getNetSalary());
            salaryViewDto.setOverTimeSalary(salary.getOverTimeSalary());
            salaryViewDto.setPayDate(salary.getPayDate());
            salaryViewDto.setSalaryStatus(salary.getSalaryStatus());

            return salaryViewDto;
            
        }).collect(Collectors.toList());

        return salaryList;
    }

    @Override
    public Salary updateSalary(Long salaryId, SalaryUpdateDto salaryUpdateDto) {
        
        
        Salary existingSalary = salaryRepo.findById(salaryId).orElse(null);

        existingSalary.setAllowances(salaryUpdateDto.getAllowances());
        existingSalary.setDeductions(salaryUpdateDto.getDeductions());
        existingSalary.setPayDate(salaryUpdateDto.getPayDate());
        existingSalary.setSalaryStatus(salaryUpdateDto.getSalaryStatus());

        return salaryRepo.save(existingSalary);
    }

    @Override
    public void deleteSalary(Long salaryId) {

        salaryRepo.deleteById(salaryId);
    }

    @Override
    public SalaryViewDto viewSalaryById(Long salaryId) {

        Salary salary = salaryRepo.findById(salaryId).orElse(null);

        SalaryViewDto salaryViewDto = new SalaryViewDto();
            salaryViewDto.setSalaryId(salaryId);
            salaryViewDto.setEmployeeName(salary.getEmployee().getFirstName());
            salaryViewDto.setBasicSalary(salary.getEmployee().getBasicSalary());
            salaryViewDto.setAllowances(salary.getAllowances());
            salaryViewDto.setDeductions(salary.getDeductions());
            salaryViewDto.setNetSalary(salary.getNetSalary());
            salaryViewDto.setOverTimeSalary(salary.getOverTimeSalary());
            salaryViewDto.setPayDate(salary.getPayDate());
            salaryViewDto.setSalaryStatus(salary.getSalaryStatus());

            return salaryViewDto;

    }

    
}
