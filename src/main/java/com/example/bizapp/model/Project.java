package com.example.bizapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * プロジェクトエンティティ
 * プロジェクト情報を管理するためのJPAエンティティクラス
 */
@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    /** プロジェクトID（自動採番） */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** プロジェクトコード（必須・一意） */
    @NotBlank(message = "プロジェクトコードは必須です")
    @Column(unique = true, nullable = false)
    private String projectCode;

    /** プロジェクト名（必須） */
    @NotBlank(message = "プロジェクト名は必須です")
    @Column(nullable = false)
    private String name;

    /** クライアント名 */
    private String clientName;

    /** 担当部署 */
    private String department;

    /** ステータス（計画中/進行中/完了/保留） */
    @Column(nullable = false)
    private String status = "計画中";

    /** 開始日 */
    private LocalDate startDate;

    /** 終了予定日 */
    private LocalDate endDate;
}
