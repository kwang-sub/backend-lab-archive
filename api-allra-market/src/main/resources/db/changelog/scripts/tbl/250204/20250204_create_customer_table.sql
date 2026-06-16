-- liquibase formatted sql
-- changeset kwang:20250204_create_customer_table.sql


CREATE TABLE customer (
      id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '고객 ID (고유 키)',
      name VARCHAR(50) NOT NULL UNIQUE COMMENT '고객 이름',
      password VARCHAR(255) NOT NULL COMMENT '비밀번호',
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
);
