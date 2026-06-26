-- ─────────────────────────────────────────────────────
-- 初期データ投入スクリプト
-- アプリケーション起動時にH2データベースへ自動投入される
-- 本番環境ではマイグレーションツール（Flyway等）の使用を推奨
-- ─────────────────────────────────────────────────────

-- 社員データ
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP001', '田中 太郎', '営業部', '部長', 'tanaka@example.com', '在籍');
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP002', '佐藤 花子', '開発部', 'シニアエンジニア', 'sato@example.com', '在籍');
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP003', '鈴木 一郎', '開発部', 'エンジニア', 'suzuki@example.com', '在籍');
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP004', '高橋 美咲', '人事部', '課長', 'takahashi@example.com', '在籍');
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP005', '伊藤 健太', '総務部', '主任', 'ito@example.com', '在籍');
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP006', '渡辺 陽子', '営業部', '営業担当', 'watanabe@example.com', '在籍');
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP007', '山本 大輔', '開発部', 'テックリード', 'yamamoto@example.com', '休職');
INSERT INTO employees (employee_code, name, department, position, email, status)
VALUES ('EMP008', '中村 さくら', '経理部', '経理担当', 'nakamura@example.com', '在籍');

-- プロジェクトデータ
INSERT INTO projects (project_code, name, client_name, department, status, start_date, end_date)
VALUES ('PRJ001', '基幹システム刷新', '株式会社ABC', '開発部', '進行中', '2026-01-15', '2026-09-30');
INSERT INTO projects (project_code, name, client_name, department, status, start_date, end_date)
VALUES ('PRJ002', 'ECサイト構築', '株式会社XYZ', '開発部', '進行中', '2026-03-01', '2026-12-31');
INSERT INTO projects (project_code, name, client_name, department, status, start_date, end_date)
VALUES ('PRJ003', '社内ポータル改修', '自社', '開発部', '計画中', '2026-07-01', '2026-10-31');
INSERT INTO projects (project_code, name, client_name, department, status, start_date, end_date)
VALUES ('PRJ004', '営業支援ツール導入', '自社', '営業部', '完了', '2025-10-01', '2026-03-31');
INSERT INTO projects (project_code, name, client_name, department, status, start_date, end_date)
VALUES ('PRJ005', 'データ分析基盤構築', '株式会社DEF', '開発部', '進行中', '2026-04-01', '2027-03-31');
