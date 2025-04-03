package com.employeemanagment.ems.service;

import java.util.List;

import com.employeemanagment.ems.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeebyId(Long id);
    List<EmployeeDto> getAllEmployee();
    EmployeeDto updateEmployee(Long id , EmployeeDto employeeDto);
    void deleteEmployee(Long id);

} 