CREATE TABLE sales_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10,2),
    region VARCHAR(100),
    date DATE,
    product VARCHAR(100)
);
