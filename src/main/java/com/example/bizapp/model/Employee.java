package com.example.bizapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社員エンティティ
 * 社員情報を管理するためのJPAエンティティクラス
 */
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    /** 社員ID（自動採番） */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 社員番号（必須・一意） */
    @NotBlank(message = "社員番号は必須です")
    @Column(unique = true, nullable = false)
    private String employeeCode;

    /** 氏名（必須） */
    @NotBlank(message = "氏名は必須です")
    @Column(nullable = false)
    private String name;

    /** 部署名 */
    private String department;

    /** 役職 */
    private String position;

    /** メールアドレス */
    @Email(message = "メールアドレスの形式が正しくありません")
    private String email;

    /** 在籍状態（在籍/休職/退職） */
    @Column(nullable = false)
    private String status = "在籍";
}
