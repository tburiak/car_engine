ALTER TABLE tasks ADD COLUMN order_id INT DEFAULT NULL;
ALTER TABLE tasks ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(order_id);
