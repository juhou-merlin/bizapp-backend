package com.example.bizapp.controller;

import com.example.bizapp.model.Employee;
import com.example.bizapp.dto.EmployeeInput;
import com.example.bizapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 社員管理APIコントローラー
 * 社員情報のCRUD操作を提供するRESTエンドポイント
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /** 全社員一覧を取得する */
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    /** 指定IDの社員を取得する */
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    /** 新規社員を登録する */
    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeInput employee) {
        Employee saved = employeeService.save(employee.toEmployee());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /** 社員情報を更新する */
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody EmployeeInput employee) {
        return employeeService.update(id, employee.toEmployee());
    }

    /** 社員を削除する */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
