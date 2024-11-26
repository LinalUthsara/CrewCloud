package com.darkgenesis.crewcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkgenesis.crewcloud.entity.Salary;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, Long>{
    
}
