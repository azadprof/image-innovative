package com.imageinnovative.employeeservice.service.impl;

import com.imageinnovative.employeeservice.dto.EmployeeDTO;
import com.imageinnovative.employeeservice.entity.EmployeeEntity;
import com.imageinnovative.employeeservice.exception.EmployeeNotFoundException;
import com.imageinnovative.employeeservice.reposiotry.EmployeeRepository;
import com.imageinnovative.employeeservice.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @NonNull
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(
                employeeRepository.save(
                        modelMapper.map(employeeDTO, EmployeeEntity.class)),
                EmployeeDTO.class);
    }

    @Override
    public ResponseEntity<EmployeeDTO> getTaxDeduction(String employeeId) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            EmployeeEntity entity = employee.get();
            EmployeeDTO dto = new EmployeeDTO();
            Double totalSalary = getTotalSalary(entity.getSalary(), entity.getDoj());
            dto.setTaxAmount(calculateTaxDeduction(totalSalary));
            dto.setCessAmount(calculateCessDeduction(totalSalary));
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setYearlySalary(entity.getSalary() * 12);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            throw new EmployeeNotFoundException("Invalid Employee Id");
        }
    }

    private Double calculateCessDeduction(Double salary) {
        double twentyFiveLakh = 2500000d;
        if(salary <= twentyFiveLakh ) {
            return 0d;
        } else {
            return (salary - twentyFiveLakh) * 0.02;
        }
    }

    private Double calculateTaxDeduction(Double totalSalary) {
        double twoLakhFity  = 250000d;
        double fiveLakh     = 500000d;
        double tenLakh      = 1000000d;
        if(totalSalary <= twoLakhFity) {
            return 0d;
        } else if (totalSalary <= fiveLakh) {
            return (totalSalary-twoLakhFity)*0.05;
        } else if (totalSalary <= tenLakh) {
            return 12500d + ((totalSalary-fiveLakh)*0.10);
        } else {
            return 12500d + 50000d + ((totalSalary-tenLakh)*0.20);
        }
    }

    private Double getTotalSalary(Double salary, LocalDate doj) {
        LocalDate startOfFY = LocalDate.of(2023, 4, 1);
        double totalSalary;
        if(doj.isBefore(startOfFY)) {
            totalSalary = salary * 12;
        } else {
            int monthsNotWorked = doj.getMonthValue() - startOfFY.getMonthValue();
            int daysNotWorked = LocalDate.of(doj.getYear(), doj.getMonthValue(), doj.getDayOfMonth()).until(doj).getDays();
            totalSalary =
                    (salary * 12) - (monthsNotWorked > 0 ?
                            (salary * monthsNotWorked) : 0) - ((salary * (30 - daysNotWorked))/30);
        }
        return totalSalary;
    }

}
