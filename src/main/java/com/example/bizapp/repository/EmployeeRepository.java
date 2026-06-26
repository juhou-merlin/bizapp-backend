package com.example.bizapp.repository;

import com.example.bizapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 社員リポジトリ
 * 社員データへのCRUDアクセスを提供する
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /** 部署名で社員を検索する */
    List<Employee> findByDepartment(String department);

    /** 在籍状態で社員を検索する */
    List<Employee> findByStatus(String status);
}
