package com.employeemanagment.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagment.ems.dto.EmployeeDto;
import com.employeemanagment.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //build Rest API for createEmployee
    @PostMapping 
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);

       return new ResponseEntity<>(savedEmployeeDto,HttpStatus.CREATED);
    }

    //Build Rest API for getEmployee by id
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        EmployeeDto employeeDto= employeeService.getEmployeebyId(id);
        return ResponseEntity.ok(employeeDto);
    }

    //Build Rest Api to get All Employees
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
       List<EmployeeDto> employeeDtos= employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeDtos);
    }

    //Build Rest Api for update employee
    
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updatedEmployee(@PathVariable Long id ,
                                                       @RequestBody EmployeeDto employeeDto){
        EmployeeDto updateEmployee= employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(updateEmployee);
    }

    //Build Rest Api for delete employee
    @DeleteMapping("{id}") 
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("SuccessFully Deleted the employee : "+ id);
    }
    
}
