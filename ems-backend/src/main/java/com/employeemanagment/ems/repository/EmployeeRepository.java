package com.employeemanagment.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagment.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
}
