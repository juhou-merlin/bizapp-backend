package com.example.bizapp.controller;

import com.example.bizapp.model.Project;
import com.example.bizapp.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * プロジェクト管理APIコントローラー
 * プロジェクト情報のCRUD操作を提供するRESTエンドポイント
 */
@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /** 全プロジェクト一覧を取得する */
    @GetMapping
    public List<Project> getAll() {
        return projectService.findAll();
    }

    /** 指定IDのプロジェクトを取得する */
    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    /** 新規プロジェクトを登録する */
    @PostMapping
    public ResponseEntity<Project> create(@Valid @RequestBody Project project) {
        Project saved = projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /** プロジェクトを削除する */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
