package com.darkgenesis.crewcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkgenesis.crewcloud.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    
}
