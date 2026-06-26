package com.example.bizapp.service;

import com.example.bizapp.model.Employee;
import com.example.bizapp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 社員サービス
 * 社員に関するビジネスロジックを提供する
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /** 全社員を取得する */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /** IDで社員を取得する */
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("社員が見つかりません: ID=" + id));
    }

    /** 社員を登録する */
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    /** 社員情報を更新する */
    public Employee update(Long id, Employee employee) {
        Employee existing = findById(id);
        existing.setName(employee.getName());
        existing.setDepartment(employee.getDepartment());
        existing.setPosition(employee.getPosition());
        existing.setEmail(employee.getEmail());
        existing.setStatus(employee.getStatus());
        return employeeRepository.save(existing);
    }

    /** 社員を削除する */
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    /** 部署で社員を検索する */
    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }
}
