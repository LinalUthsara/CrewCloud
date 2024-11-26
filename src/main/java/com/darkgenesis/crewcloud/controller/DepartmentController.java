package com.darkgenesis.crewcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkgenesis.crewcloud.entity.Department;
import com.darkgenesis.crewcloud.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/department")
@CrossOrigin("*")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }

    @GetMapping
    public ResponseEntity<List<Department>> viewAllDepartment(){
        List<Department>departments = departmentService.viewAllDepartment();
        return ResponseEntity.status(HttpStatus.OK).body(departments);

    }
    
    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> viewDepartmentById(@PathVariable Long departmentId){
        Department department = departmentService.viewDepartmentById(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(department);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long departmentId, @RequestBody Department department){
        Department updatedDepartment = departmentService.updateDepartment(departmentId, department);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteDepartment(@PathVariable Long departmentId){
        departmentService.deleteDepartment(departmentId);
    }
    
}
