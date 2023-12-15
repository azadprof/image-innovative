package com.imageinnovative.employeeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity(name = "employee_contact")
@Data
public class EmployeeContacts {

    @Id
    @Column (name = "employee_id", nullable = false)
    @JdbcTypeCode (SqlTypes.VARCHAR)
    private String employeeId;

    private Long phoneNumber;
}
