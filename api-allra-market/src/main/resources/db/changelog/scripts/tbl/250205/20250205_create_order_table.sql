-- liquibase formatted sql
-- changeset kwang:20250205_create_order_table.sql

CREATE TABLE orders (
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '주문 ID (고유 키)',
     customer_id BIGINT NOT NULL COMMENT '고객 ID',
     order_status VARCHAR(25) NOT NULL COMMENT '주문상태',
     total_amount BIGINT NOT NULL COMMENT '주문금액',
     payment_amount BIGINT NOT NULL COMMENT '결제금액',
     order_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '주문 시간',
     created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
     updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
);

ALTER TABLE orders ADD CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customer(id);

CREATE TABLE order_product (
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '주문 상품 ID (고유 키)',
     order_id BIGINT NOT NULL COMMENT '주문 ID',
     product_id BIGINT NOT NULL COMMENT '상품 ID',
     name VARCHAR(255) NOT NULL COMMENT '상품 이름',
     description VARCHAR(1000) COMMENT '상품 설명',
     price BIGINT NOT NULL COMMENT '상품 가격',
     quantity INT NOT NULL COMMENT '수량',
     created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
     updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
);

ALTER TABLE order_product ADD CONSTRAINT fk_order_product_customer FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE order_product ADD CONSTRAINT fk_order_product_product FOREIGN KEY (product_id) REFERENCES product(id);