create database finkraft;
use  finkraft;
CREATE TABLE transactions (
    transaction_id varchar(50),
    customer_name VARCHAR(100) NOT NULL,
    transaction_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    invoice_url VARCHAR(255)
);

select * from  transactions;
