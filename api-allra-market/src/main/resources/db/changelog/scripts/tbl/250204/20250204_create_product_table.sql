-- liquibase formatted sql
-- changeset kwang:20250204_create_product_table.sql

CREATE TABLE product (
     id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 ID (고유 키)',
     name VARCHAR(255) NOT NULL COMMENT '상품 이름',
     description VARCHAR(1000) COMMENT '상품 설명',
     price BIGINT NOT NULL COMMENT '상품 가격',
     stock INT NOT NULL COMMENT '재고 수량',
     created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
     updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
);
