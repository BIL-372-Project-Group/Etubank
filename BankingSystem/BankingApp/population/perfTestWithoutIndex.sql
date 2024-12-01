-- Enable profiling for baseline measurement
SET profiling = 1;

-- Run queries to test the baseline performance
-- Run the first query and allow a slight delay to simulate load
DO SLEEP(0.1);

-- Query 1: Simple filter on the bank table
SELECT * FROM bank WHERE branch_code = '5';

-- Query 2: Search by email in the customer table
SELECT * FROM customer WHERE email LIKE '%gmail.com%';

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

-- Show profiling results to see execution times
SHOW PROFILES;
