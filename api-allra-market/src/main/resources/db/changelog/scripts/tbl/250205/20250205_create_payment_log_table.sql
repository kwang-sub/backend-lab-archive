-- liquibase formatted sql
-- changeset kwang:20250205_create_payment_log_table.sql

CREATE TABLE payment_log (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '결제 요청 내역 ID (고유 키)',
    order_id BIGINT NOT NULL COMMENT '주문 ID',
    status VARCHAR(25) NOT NULL COMMENT '결제상태',
    transaction_id VARCHAR(100) NULL COMMENT '외부 결제 시스템 ID',
    failure_reason VARCHAR(100) NULL COMMENT '실패 사유',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 시간',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정 시간'
);

ALTER TABLE payment_log ADD CONSTRAINT fk_payment_log_order FOREIGN KEY (order_id) REFERENCES orders(id);