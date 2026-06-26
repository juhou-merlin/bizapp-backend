# 業務管理システム — バックエンド

## 概要

日本企業向けの汎用業務管理システムのバックエンド REST API です。
社員管理・プロジェクト管理・ダッシュボード集計などの機能を提供します。

このリポジトリは、OpenShift 上で CI/CD パイプライン（Tekton）と GitOps（ArgoCD）を
構築・学習するためのサンプルアプリケーションとして設計されています。

## 技術スタック

| 技術 | バージョン | 用途 |
|------|-----------|------|
| Java | OpenJDK 21（v0.1仕様: 25固定） | 実行環境 |
| Spring Boot | 3.5.x | Web フレームワーク |
| Spring Data JPA | — | データアクセス（ORM） |
| Maven | 3.9.x（mvnw 対応） | ビルドツール |
| H2 Database | — | 開発用インメモリDB |
| PostgreSQL | — | 本番用データベース |
| Lombok | — | ボイラープレートコード削減 |
| Spring Boot Actuator | — | ヘルスチェック・メトリクス |

## ディレクトリ構成

```
backend/
├── .s2i/
│   └── environment              ← OpenShift S2I ビルド設定
├── .mvn/wrapper/
│   └── maven-wrapper.properties ← Maven Wrapper バージョン設定
├── pom.xml                      ← Maven ビルド設定
│
└── src/
    ├── main/
    │   ├── java/com/example/bizapp/
    │   │   ├── BizAppApplication.java          ← アプリケーション起動クラス
    │   │   ├── controller/
    │   │   │   ├── EmployeeController.java     ← 社員管理 REST API
    │   │   │   ├── ProjectController.java      ← プロジェクト管理 REST API
    │   │   │   └── DashboardController.java    ← ダッシュボード集計 API
    │   │   ├── model/
    │   │   │   ├── Employee.java               ← 社員エンティティ
    │   │   │   └── Project.java                ← プロジェクトエンティティ
    │   │   ├── repository/
    │   │   │   ├── EmployeeRepository.java     ← 社員データアクセス
    │   │   │   └── ProjectRepository.java      ← プロジェクトデータアクセス
    │   │   └── service/
    │   │       ├── EmployeeService.java         ← 社員ビジネスロジック
    │   │       └── ProjectService.java          ← プロジェクトビジネスロジック
    │   │
    │   └── resources/
    │       ├── application.yaml                 ← アプリケーション設定
    │       └── data.sql                         ← 初期データ投入スクリプト
    │
    └── test/java/com/example/bizapp/
        ├── BizAppApplicationTests.java          ← 起動テスト
        └── service/
            └── EmployeeServiceTest.java         ← 社員サービスのユニットテスト
```

## API エンドポイント

### 社員管理 (`/api/employees`)

| メソッド | パス | 説明 |
|---------|------|------|
| GET | `/api/employees` | 全社員一覧を取得 |
| GET | `/api/employees/{id}` | 指定IDの社員を取得 |
| POST | `/api/employees` | 新規社員を登録 |
| PUT | `/api/employees/{id}` | 社員情報を更新 |
| DELETE | `/api/employees/{id}` | 社員を削除 |

### プロジェクト管理 (`/api/projects`)

| メソッド | パス | 説明 |
|---------|------|------|
| GET | `/api/projects` | 全プロジェクト一覧を取得 |
| GET | `/api/projects/{id}` | 指定IDのプロジェクトを取得 |
| POST | `/api/projects` | 新規プロジェクトを登録 |
| DELETE | `/api/projects/{id}` | プロジェクトを削除 |

### ダッシュボード (`/api/dashboard`)

| メソッド | パス | 説明 |
|---------|------|------|
| GET | `/api/dashboard` | 集計データ（社員数・プロジェクト数等）を取得 |

### ヘルスチェック

| パス | 説明 |
|------|------|
| `/actuator/health` | アプリケーションの稼働状態（Kubernetes Probe 用） |

## ローカル開発

### 前提条件

- JDK 21 以上
- Maven 3.9 以上（または同梱の mvnw を使用）

### ビルドとテスト

```bash
# ユニットテスト実行 + JAR パッケージング（CI/CD パイプラインと同じコマンド）
./mvnw clean package

# ユニットテストのみ実行
./mvnw clean test
```

### ローカル起動

```bash
# ビルド後に起動（H2 インメモリDB を使用）
./mvnw spring-boot:run

# アクセス
# API:         http://localhost:8080/api/employees
# H2コンソール: http://localhost:8080/h2-console
# ヘルスチェック: http://localhost:8080/actuator/health
```

### 初期データ

起動時に `data.sql` から以下のサンプルデータが自動投入されます：

- 社員 8 名（営業部・開発部・人事部・総務部・経理部）
- プロジェクト 5 件（進行中・計画中・完了）

## OpenShift へのデプロイ

### CI/CD パイプラインによる自動デプロイ

このリポジトリに Push すると、以下のパイプラインが自動実行されます：

```
git-clone → maven-build-test → s2i-java-build → git-update-manifest
                                                        ↓
                                                 ArgoCD が検知して自動デプロイ
```

- **main ブランチ**: ビルド → テスト → S2I イメージビルド → デプロイ
- **main 以外**: ビルド → テストのみ（デプロイは行わない）

### S2I（Source-to-Image）

このアプリケーションは **S2I 方式** でコンテナイメージを生成します。
開発者が Dockerfile / Containerfile を記述する必要はありません。

- Red Hat 提供の OpenJDK ランタイムイメージをベースに使用
- パイプラインが `mvn clean package` でビルドした JAR を自動的にイメージに組み込む
- `.s2i/environment` に S2I ビルド時の設定を記述

## 関連リポジトリ

| リポジトリ | 用途 |
|-----------|------|
| [bizapp-frontend](https://github.com/juhou-merlin/bizapp-frontend) | フロントエンド（Next.js） |
| [bizapp-manifests-app](https://github.com/juhou-merlin/bizapp-manifests-app) | アプリ用マニフェスト（Kustomize） |
