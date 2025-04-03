package com.employeemanagment.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.employeemanagment.ems.dto.EmployeeDto;
import com.employeemanagment.ems.entity.Employee;
import com.employeemanagment.ems.exception.ResourceNotFoundException;
import com.employeemanagment.ems.mapper.EmployeeMapper;
import com.employeemanagment.ems.repository.EmployeeRepository;
import com.employeemanagment.ems.service.EmployeeService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.maptToemployeeDto(savedEmployee);

        
    }

    @Override
    public EmployeeDto getEmployeebyId(Long id) {

        Employee employee = employeeRepository.findById(id)
                                              .orElseThrow(()->
                                               new ResourceNotFoundException("Employee  does not exist with the given id :"+id)); 

       return EmployeeMapper.maptToemployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
          
      List<EmployeeDto> employeeDtos= employeeRepository.findAll()
                                                 .stream()
                                                 .map((employee)->EmployeeMapper.maptToemployeeDto(employee))
                                                 .collect(Collectors.toList());   

        return employeeDtos;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                                              .orElseThrow(() -> 
                                                            new ResourceNotFoundException("Employee does not exist with employee id : "+ id));
                                                               
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());                                                      
        
        Employee updatedEmployee =employeeRepository.save(employee);
        return EmployeeMapper.maptToemployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                                              .orElseThrow(() -> 
                                                            new ResourceNotFoundException("Employee does not exist with employee id : "+ id));
            employeeRepository.delete(employee);
            

    }
    
    
}
