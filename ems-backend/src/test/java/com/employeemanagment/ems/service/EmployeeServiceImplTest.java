package com.employeemanagment.ems.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.employeemanagment.ems.dto.EmployeeDto;
import com.employeemanagment.ems.entity.Employee;
import com.employeemanagment.ems.exception.ResourceNotFoundException;
import com.employeemanagment.ems.mapper.EmployeeMapper;
import com.employeemanagment.ems.repository.EmployeeRepository;
import com.employeemanagment.ems.service.impl.EmployeeServiceImpl;

import jakarta.persistence.EntityNotFoundException;



@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks 
    EmployeeServiceImpl employeeServiceImpl;

    @Mock
    EmployeeRepository employeeRepository;


    @Test
    void createEmployeeTest(){
        Employee employee = new Employee((long) 1,"kajal","Sah","kajalsah22gmail.com");
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        EmployeeDto employeeDto =employeeServiceImpl.createEmployee(EmployeeMapper.maptToemployeeDto(employee));
        //Aseertions  
        Assertions.assertEquals(employee.getId(), employeeDto.getId());
        Assertions.assertEquals(employee.getFirstName(), employeeDto.getFirstName());
        Assertions.assertEquals(employee.getLastName(), employeeDto.getLastName()); 
        Assertions.assertEquals(employee.getEmail(), employeeDto.getEmail());
    }
     
    @Test
    void getEmployeebyIdTest(){
        Employee employee = new Employee((long) 1,"kajal","Sah","kajalsah22gmail.com");
        Long existentEmployeeId = 2L;

        Mockito.when(employeeRepository.findById(existentEmployeeId)).thenReturn(Optional.of(employee));
        EmployeeDto employeeDto =employeeServiceImpl.getEmployeebyId(1L);

        Assertions.assertEquals(employee.getId(), employeeDto.getId());
        Assertions.assertEquals(employee.getFirstName(), employeeDto.getFirstName());
        Assertions.assertEquals(employee.getLastName(), employeeDto.getLastName());
        Assertions.assertEquals(employee.getEmail(), employeeDto.getEmail());
         

    }

    @Test
    void testGetEmployeeByIdThrowsResourceNotFoundException() {
       
        Long nonExistentEmployeeId = 2L;
        Mockito.when(employeeRepository.findById(nonExistentEmployeeId)).thenReturn(Optional.empty());

       
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImpl.getEmployeebyId(nonExistentEmployeeId);
        });
       
    }
    
    @Test
    void testGetAllEmployee(){
        Employee employee = new Employee(1L,"Kajal","Sah","kajalsah2@gmail.com");
        List<Employee> employees = new ArrayList<>(); 
        employees.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDto> employeeDtos = employeeServiceImpl.getAllEmployee();

        Assertions.assertEquals(employees.getFirst().getId(), employeeDtos.getFirst().getId());
        

    }

    @Test
    void testUpdateEmployee(){
        Long existentEmployeeId=1L;
        EmployeeDto updateEmployeeDto = new EmployeeDto(existentEmployeeId ,"kajal","sah","kajal.sah@yahoo.com"); 
        Employee updatedEmployee = new Employee(1L,"kajal","Sah","kajalsah2@gmail.com");
        
        
        Mockito.when(employeeRepository.findById(existentEmployeeId)).thenReturn(Optional.of(updatedEmployee));
        
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(updatedEmployee);

        EmployeeDto savedEmployee =employeeServiceImpl.updateEmployee(existentEmployeeId,updateEmployeeDto);

        Assertions.assertEquals(updatedEmployee.getId(), savedEmployee.getId());
        Assertions.assertEquals(updatedEmployee.getFirstName(), savedEmployee.getFirstName());
        Assertions.assertEquals(updatedEmployee.getLastName(), savedEmployee.getLastName());
        Assertions.assertEquals(updatedEmployee.getEmail(), savedEmployee.getEmail());

    }


    @Test
    void testUpdateEmployeeThorwResourceNotFoundException(){
      Long nonExistentEmployeeId=1L;
      EmployeeDto employeeDto = new EmployeeDto(nonExistentEmployeeId ,"Siri","Narayan","sirya@gmail.com");
      Mockito.when(employeeRepository.findById(nonExistentEmployeeId)).thenReturn(Optional.empty());
      
      assertThrows(ResourceNotFoundException.class, () -> {
        employeeServiceImpl.updateEmployee(nonExistentEmployeeId, employeeDto);
    });

    }

    @Test
    void testdeleteEmployeeShouldDeleteEmployee(){
        Long id =1L; 
        Employee employee = new Employee(id,"Kajal","sah","kajalsah2@gmail.com");
        doNothing().when(employeeRepository).delete(employee);
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
       
        employeeServiceImpl.deleteEmployee(id);

        Mockito.verify(employeeRepository,times(1)).delete(employee);

    }

    @Test
    void testdeleteEmployeeResourceNotFoundException(){
        Long id =1L; 
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.empty());
      
        assertThrows(ResourceNotFoundException.class, () ->{
            employeeServiceImpl.deleteEmployee(id);
        });
    }


    
}
