package com.darkgenesis.crewcloud.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkgenesis.crewcloud.entity.Employee;
import com.darkgenesis.crewcloud.entity.WorkSchedule;

@Repository
public interface WorkScheduleRepo extends JpaRepository<WorkSchedule, Long>{

    WorkSchedule findByEmployeeAndDate(Employee employee, LocalDate date);
    List<WorkSchedule> findAllByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);
    List<WorkSchedule> findByEmployee(Employee employee);
}
