package com.example.bizapp.dto;

import com.example.bizapp.model.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/** 登録・更新で受け付ける社員項目。 */
public record EmployeeInput(@NotBlank String employeeCode, @NotBlank String name,
        String department, String position, @Email String email, String status) {
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeCode(employeeCode);
        employee.setName(name);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setEmail(email);
        employee.setStatus(status == null ? "在籍" : status);
        return employee;
    }
}
