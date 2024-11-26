package com.darkgenesis.crewcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkgenesis.crewcloud.entity.Leave;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long>{
    
}
