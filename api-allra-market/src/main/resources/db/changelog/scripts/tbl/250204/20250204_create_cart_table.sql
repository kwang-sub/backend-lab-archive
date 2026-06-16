-- liquibase formatted sql
-- changeset kwang:20250204_create_cart_table.sql

CREATE TABLE cart (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 ID (고유 키)',
    customer_id BIGINT NOT NULL COMMENT '고객 ID',
    product_id BIGINT NOT NULL COMMENT '상품 ID',
    quantity INT NOT NULL COMMENT '수량',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
);

ALTER TABLE cart ADD CONSTRAINT fk_cart_customer FOREIGN KEY (customer_id) REFERENCES customer(id);
ALTER TABLE cart ADD CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product(id);
ALTER TABLE cart ADD CONSTRAINT uk_cart_customer_product UNIQUE (customer_id, product_id);