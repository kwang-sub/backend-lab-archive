-- liquibase formatted sql
-- changeset kwang:20250205_add_column_payment_log.sql

ALTER TABLE payment_log ADD COLUMN type VARCHAR(25) NOT NULL COMMENT '유형' AFTER order_id;

ALTER TABLE payment_log DROP CONSTRAINT fk_payment_log_order;
ALTER TABLE payment_log MODIFY COLUMN order_id BIGINT NULL COMMENT '주문 ID';
