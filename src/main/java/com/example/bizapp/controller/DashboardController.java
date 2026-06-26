package com.example.bizapp.controller;

import com.example.bizapp.repository.EmployeeRepository;
import com.example.bizapp.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * ダッシュボードAPIコントローラー
 * フロントエンドのダッシュボード画面に表示する集計データを提供する
 */
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public DashboardController(EmployeeRepository employeeRepository,
                               ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    /** ダッシュボード用の集計データを返す */
    @GetMapping
    public Map<String, Object> getSummary() {
        long totalEmployees = employeeRepository.count();
        long activeEmployees = employeeRepository.findByStatus("在籍").size();
        long totalProjects = projectRepository.count();
        long activeProjects = projectRepository.findByStatus("進行中").size();

        return Map.of(
            "totalEmployees", totalEmployees,
            "activeEmployees", activeEmployees,
            "totalProjects", totalProjects,
            "activeProjects", activeProjects
        );
    }
}
