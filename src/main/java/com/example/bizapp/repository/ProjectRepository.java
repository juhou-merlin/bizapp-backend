package com.example.bizapp.repository;

import com.example.bizapp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * プロジェクトリポジトリ
 * プロジェクトデータへのCRUDアクセスを提供する
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    /** ステータスでプロジェクトを検索する */
    List<Project> findByStatus(String status);
}
