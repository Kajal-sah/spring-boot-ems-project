package com.employeemanagment.ems.mapper;

import com.employeemanagment.ems.dto.EmployeeDto;
import com.employeemanagment.ems.entity.Employee;

public class EmployeeMapper {
    

    public static EmployeeDto maptToemployeeDto(Employee employee){


        return new EmployeeDto(employee.getId(),
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getEmail());
        
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){

        return new Employee(employeeDto.getId(),
                            employeeDto.getFirstName(),
                            employeeDto.getLastName(),
                            employeeDto.getEmail());
    }
}
