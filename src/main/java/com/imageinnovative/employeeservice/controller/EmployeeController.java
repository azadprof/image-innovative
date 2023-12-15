package com.imageinnovative.employeeservice.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

import com.imageinnovative.employeeservice.dto.EmployeeDTO;
import com.imageinnovative.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    @NonNull
    private EmployeeService employeeServiceImpl;

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world!";
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO)
    {
        return new ResponseEntity<>(employeeServiceImpl.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeTaxDeduction(@PathVariable("employeeId") String employeeId) throws Exception {
        return employeeServiceImpl.getTaxDeduction(employeeId);
    }
}
