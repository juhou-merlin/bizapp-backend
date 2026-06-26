package com.example.bizapp.service;

import com.example.bizapp.model.Project;
import com.example.bizapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * プロジェクトサービス
 * プロジェクトに関するビジネスロジックを提供する
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /** 全プロジェクトを取得する */
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    /** IDでプロジェクトを取得する */
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("プロジェクトが見つかりません: ID=" + id));
    }

    /** プロジェクトを登録する */
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    /** プロジェクトを削除する */
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
