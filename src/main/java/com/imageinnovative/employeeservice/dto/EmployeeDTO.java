package com.imageinnovative.employeeservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {


    @NotNull(message = "Employee Id can't be blank")
    private String employeeId;

    @NotNull(message = "First Name can't be blank")
    private String firstName;

    @NotNull(message = "Last Name can't be blank")
    private String lastName;

    @NotNull(message = "Email can't be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "At least one valid phone number is required")
    private List<Long> phoneNumbers;

    @NotNull(message = "Date of Joining can't be blank")
    private LocalDate doj;

    @NotNull(message = "Salary can't be blank")
    private Double salary;

    private Double yearlySalary;

    private Double taxAmount;

    private Double cessAmount;
}
