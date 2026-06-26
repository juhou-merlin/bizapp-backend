package com.example.bizapp.service;

import com.example.bizapp.model.Employee;
import com.example.bizapp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 社員サービスのユニットテスト
 * 社員管理に関するビジネスロジックの正常動作を検証する
 */
@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    @DisplayName("社員の登録と取得が正常に動作すること")
    void testCreateAndFind() {
        Employee employee = new Employee();
        employee.setEmployeeCode("TEST001");
        employee.setName("テスト太郎");
        employee.setDepartment("開発部");
        employee.setPosition("エンジニア");
        employee.setEmail("test@example.com");
        employee.setStatus("在籍");

        Employee saved = employeeService.save(employee);
        assertNotNull(saved.getId());

        Employee found = employeeService.findById(saved.getId());
        assertEquals("テスト太郎", found.getName());
        assertEquals("開発部", found.getDepartment());
    }

    @Test
    @DisplayName("全社員一覧の取得が正常に動作すること")
    void testFindAll() {
        Employee emp1 = new Employee();
        emp1.setEmployeeCode("TEST001");
        emp1.setName("社員A");
        emp1.setStatus("在籍");
        employeeService.save(emp1);

        Employee emp2 = new Employee();
        emp2.setEmployeeCode("TEST002");
        emp2.setName("社員B");
        emp2.setStatus("在籍");
        employeeService.save(emp2);

        List<Employee> all = employeeService.findAll();
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("社員の削除が正常に動作すること")
    void testDelete() {
        Employee employee = new Employee();
        employee.setEmployeeCode("TEST001");
        employee.setName("削除テスト");
        employee.setStatus("在籍");

        Employee saved = employeeService.save(employee);
        employeeService.delete(saved.getId());

        assertThrows(RuntimeException.class, () -> employeeService.findById(saved.getId()));
    }
}
