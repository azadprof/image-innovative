package com.imageinnovative.employeeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Employee")
@Data
public class EmployeeEntity {

    @Id
    @Column (name = "employee_id", nullable = false)
    @JdbcTypeCode (SqlTypes.VARCHAR)
    private String employeeID;

    private String firstName;

    private String lastName;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    @OneToMany
    private List<EmployeeContacts> phoneNumbers;

    private LocalDate doj;

    private double salary;

}
