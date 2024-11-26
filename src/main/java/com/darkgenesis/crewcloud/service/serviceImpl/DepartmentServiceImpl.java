package com.darkgenesis.crewcloud.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkgenesis.crewcloud.entity.Department;
import com.darkgenesis.crewcloud.exception.DepartmentNotFoundException;
import com.darkgenesis.crewcloud.repository.DepartmentRepo;
import com.darkgenesis.crewcloud.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public List<Department> viewAllDepartment() {
        return departmentRepo.findAll();
    }

    @Override
    public Department viewDepartmentById(Long departmentId) {
        return departmentRepo.findById(departmentId).orElseThrow(() ->new DepartmentNotFoundException("Department not found"));
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department existingDepartment = departmentRepo.findById(departmentId).orElseThrow(() ->new DepartmentNotFoundException("Department not found"));
        
        existingDepartment.setDepartmentName(department.getDepartmentName());
        return departmentRepo.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {  

        departmentRepo.deleteById(departmentId);
    }


}
