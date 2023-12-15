package com.imageinnovative.employeeservice.service;

import com.imageinnovative.employeeservice.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    ResponseEntity<EmployeeDTO> getTaxDeduction(String employeeId);
}
