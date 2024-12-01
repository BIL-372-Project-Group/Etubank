-- Create indexes on commonly queried columns
ALTER TABLE bank ADD INDEX idx_branch_code (branch_code);

ALTER TABLE customer ADD INDEX idx_email (email);
ALTER TABLE account ADD INDEX idx_balance (balance);
ALTER TABLE card ADD INDEX idx_expiry_date (expiry_date);
ALTER TABLE transaction ADD INDEX idx_amount_date (amount, transaction_date);
ALTER TABLE loan ADD INDEX idx_end_date (end_date);
ALTER TABLE loan_payment_schedule ADD INDEX idx_status (status);
ALTER TABLE loan_payment ADD INDEX idx_amount_paid (amount_paid);

-- Enable profiling again to measure the after-indexing performance
SET profiling = 1;

-- Run the same queries after indexing
DO SLEEP(0.1);

-- Query 1: Simple filter on the bank table
SELECT * FROM bank WHERE branch_code = 'A123';

-- Query 2: Search by email in the customer table
SELECT * FROM customer WHERE email LIKE '%example.com%';

-- Query 3: Select with a condition on the account balance
SELECT * FROM account WHERE balance > 1000.00;

-- Query 4: Filter with sorting on the card table
SELECT * FROM card WHERE expiry_date > NOW() ORDER BY expiry_date DESC LIMIT 10;

-- Query 5: Filter with ordering and limit on transactions
SELECT * FROM transaction WHERE amount > 500.00 ORDER BY transaction_date DESC LIMIT 100;

-- Query 6: Query on loans with null end dates
SELECT * FROM loan WHERE end_date IS NULL;

-- Query 7: Check unpaid loan payment schedules
SELECT * FROM loan_payment_schedule WHERE status = 0;

-- Query 8: Filter on the loan payment table for a certain payment condition
SELECT * FROM loan_payment WHERE amount_paid > 100.00;

-- Show profiling results for the after-indexing execution times
SHOW PROFILES;
