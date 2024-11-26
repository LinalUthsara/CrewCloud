package com.darkgenesis.crewcloud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.entity.Department;

@Service
public interface DepartmentService {
    
    Department createDepartment(Department department);
    List<Department> viewAllDepartment();
    Department viewDepartmentById(Long departmentId);
    Department updateDepartment(Long departmentId, Department department);
    void deleteDepartment(Long departmentId);
}
