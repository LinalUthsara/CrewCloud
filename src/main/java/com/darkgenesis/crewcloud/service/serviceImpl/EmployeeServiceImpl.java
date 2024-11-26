package com.darkgenesis.crewcloud.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.dto.EmployeeDto;
import com.darkgenesis.crewcloud.dto.EmployeeViewDto;
import com.darkgenesis.crewcloud.entity.Administrator;
import com.darkgenesis.crewcloud.entity.Department;
import com.darkgenesis.crewcloud.entity.Employee;
import com.darkgenesis.crewcloud.entity.RegularEmployee;
import com.darkgenesis.crewcloud.exception.DepartmentNotFoundException;
import com.darkgenesis.crewcloud.repository.DepartmentRepo;
import com.darkgenesis.crewcloud.repository.EmployeeRepo;
import com.darkgenesis.crewcloud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;
    
    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {

        Employee employee;

        if ("ADMINISTRATOR".equalsIgnoreCase(employeeDto.getRole())){
            employee = new Administrator();
        }else{
            employee = new RegularEmployee();
        }

        employee.setNic(employeeDto.getNic());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPassword(employeeDto.getPassword());
        employee.setBasicSalary(employeeDto.getBasicSalary());
        employee.setDesignation(employeeDto.getDesignation());

        Department department = departmentRepo.findById(employeeDto.getDepartmentId()).orElseThrow(() ->new DepartmentNotFoundException("Department not found"));
        employee.setDepartment(department);

        return employeeRepo.save(employee);
        
    }

    @Override
    public List<EmployeeViewDto> viewAllEmployee() {
        List<Employee> employees = employeeRepo.findAll();

        List<EmployeeViewDto> employeeList = employees.stream().map(employee ->{

            EmployeeViewDto employeeViewDto = new EmployeeViewDto();
            employeeViewDto.setEmployeeId(employee.getEmployeeId());
            employeeViewDto.setNic(employee.getNic());
            employeeViewDto.setFirstName(employee.getFirstName());
            employeeViewDto.setLastName(employee.getLastName());
            employeeViewDto.setDesignation(employee.getDesignation());
            employeeViewDto.setBasicSalary(employee.getBasicSalary());

            if(employee instanceof Administrator){
                employeeViewDto.setRole("ADMINISTRATOR");
            }else if(employee instanceof RegularEmployee){
                employeeViewDto.setRole("EMPLOYEE");
            }

            if (employee.getDepartment() != null) {
                employeeViewDto.setDepartmentName(employee.getDepartment().getDepartmentName());
            } else {
                employeeViewDto.setDepartmentName("No Department Assigned");
            }
            
            return employeeViewDto;
            
        }).collect(Collectors.toList());

        return employeeList;
    }

    @Override
    public EmployeeViewDto viewEmployeeById(Long employeeId) {
        
        Employee employee = employeeRepo.findById(employeeId).orElse(null);

        EmployeeViewDto employeeViewDto = new EmployeeViewDto();
            employeeViewDto.setEmployeeId(employee.getEmployeeId());
            employeeViewDto.setNic(employee.getNic());
            employeeViewDto.setFirstName(employee.getFirstName());
            employeeViewDto.setLastName(employee.getLastName());
            employeeViewDto.setDesignation(employee.getDesignation());
            employeeViewDto.setBasicSalary(employee.getBasicSalary());

            if(employee instanceof Administrator){
                employeeViewDto.setRole("ADMINISTRATOR");
            }else if(employee instanceof RegularEmployee){
                employeeViewDto.setRole("EMPLOYEE");
            }

            if (employee.getDepartment() != null) {
                employeeViewDto.setDepartmentName(employee.getDepartment().getDepartmentName());
            } else {
                employeeViewDto.setDepartmentName("No Department Assigned");
            }
            
            return employeeViewDto;
    };

    @Override
    public Employee updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        
        Employee existingEmployee = employeeRepo.findById(employeeId).orElse(null);

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setDesignation(employeeDto.getDesignation());
        existingEmployee.setBasicSalary(employeeDto.getBasicSalary());
        
        return employeeRepo.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        employeeRepo.deleteById(employeeId);
    }
}
