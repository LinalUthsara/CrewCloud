package com.darkgenesis.crewcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkgenesis.crewcloud.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{
    
}
