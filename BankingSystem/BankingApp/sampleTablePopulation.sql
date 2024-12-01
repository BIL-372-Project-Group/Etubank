-- Populating the `bank` table
INSERT INTO bank (bank_id, name, address, contact_number, branch_code) VALUES
(1, 'Global Bank', '123 Finance Street, New York, NY', '+1-555-0101', 'GBNY001'),
(2, 'Future Bank', '456 Innovation Ave, San Francisco, CA', '+1-555-0202', 'FBSF002'),
(3, 'Secure Bank', '789 Security Blvd, Chicago, IL', '+1-555-0303', 'SBCH003');

-- Populating the `customer` table
INSERT INTO customer (customer_id, bank_id, first_name, last_name, date_of_birth, nationality, nin, last_login_date, email, phone_number, address, city, state, postal_code, country_of_residence, password) VALUES
(1, 1, 'John', 'Doe', '1985-08-15', 'American', '123456789', '2024-11-28 15:30:00', '1', '+1-555-1234', '100 Elm St', 'New York', 'NY', '10001', 'USA', '1'),
(2, 2, 'Jane', 'Smith', '1990-01-20', 'Canadian', '987654321', '2024-11-29 12:00:00', 'jane.smith@example.com', '+1-555-5678', '200 Oak St', 'San Francisco', 'CA', '94105', 'USA', 'hashed_password_2'),
(3, 3, 'Alice', 'Johnson', '1975-05-10', 'British', '543216789', '2024-11-25 10:00:00', 'alice.johnson@example.com', '+1-555-9101', '300 Pine St', 'Chicago', 'IL', '60605', 'USA', 'hashed_password_3');

-- Populating the `account_status` table
INSERT INTO account_status (account_status_id, type_name, description) VALUES
(1, 'Active', 'Account is active and operational'),
(2, 'Inactive', 'Account is not currently active'),
(3, 'Suspended', 'Account is suspended due to policy violations or other reasons');

-- Populating the `account_type` table
INSERT INTO account_type (account_type_id, type_name, description) VALUES
(1, 'Savings', 'Savings account for individuals'),
(2, 'Checking', 'Checking account for daily transactions'),
(3, 'Business', 'Business account for organizations');

-- Populating the `account` table
INSERT INTO account (account_id, customer_id, iban, account_number, account_status_id, account_type_id, date_opened, last_activity_date, balance, currency, bank_id) VALUES
(1, 1, 'US123456789012345678901', '100001', 1, 1, '2022-01-01', '2024-11-27 10:30:00', 2500.50, 'USD', 1),
(2, 2, 'US987654321098765432101', '200001', 1, 2, '2021-06-15', '2024-11-28 09:00:00', 15000.75, 'USD', 2),
(3, 3, 'US567890123456789012301', '300001', 2, 3, '2020-03-10', '2024-11-25 16:00:00', 50000.00, 'USD', 3);

-- Populating the `card_type` table
INSERT INTO card_type (card_type_id, type_name, description) VALUES
(1, 'Credit', 'Credit card with spending limit'),
(2, 'Debit', 'Debit card linked to account balance'),
(3, 'Prepaid', 'Prepaid card with preloaded funds');

-- Populating the `card` table
INSERT INTO card (card_id, account_id, card_type_id, card_number, card_limit, expiry_date, cvv, bank_id) VALUES
(1, 1, 1, '1234567812345678', 5000.00, '2025-12-31', '123', 1),
(2, 2, 2, '9876543298765432', NULL, '2026-11-30', '456', 2),
(3, 3, 3, '5678901256789012', 1000.00, '2024-10-31', '789', 3);

-- Populating the `transaction_type` table
INSERT INTO transaction_type (transaction_type_id, type_name, description) VALUES
(1, 'Deposit', 'Adding funds to an account'),
(2, 'Withdrawal', 'Removing funds from an account'),
(3, 'Transfer', 'Transferring funds between accounts');

-- Populating the `transaction` table
INSERT INTO transaction (transaction_id, transaction_type_id, sender_id, recipient_id, amount, transaction_date, bank_id) VALUES
(1, 1, NULL, 1, 1000.00, '2024-11-26 14:00:00', 1),
(2, 2, 2, NULL, 500.00, '2024-11-27 15:00:00', 2),
(3, 3, 1, 3, 200.00, '2024-11-28 16:00:00', 3);

-- Populating the `transaction_history` table
INSERT INTO transaction_history (history_id, account_id, transaction_id, transaction_date, balance_after_transaction, bank_id) VALUES
(1, 1, 1, '2024-11-26 14:00:00', 3500.50, 1),
(2, 2, 2, '2024-11-27 15:00:00', 14500.75, 2),
(3, 3, 3, '2024-11-28 16:00:00', 49800.00, 3);

-- Populating the `loan_status` table
INSERT INTO loan_status (loan_status_id, type_name, description) VALUES
(1, 'Pending', 'Loan is under review'),
(2, 'Approved', 'Loan has been approved'),
(3, 'Rejected', 'Loan has been rejected');

-- Populating the `loan_type` table
INSERT INTO loan_type (loan_type_id, type_name, description) VALUES
(1, 'Home Loan', 'Loan for purchasing a house'),
(2, 'Car Loan', 'Loan for purchasing a car'),
(3, 'Personal Loan', 'Loan for personal use');

-- Populating the `loan` table
INSERT INTO loan (loan_id, loan_type_id, customer_id, principal_amount, interest_rate, loan_status_id, start_date, end_date, bank_id) VALUES
(1, 1, 1, 200000, 3.5, 2, '2023-01-01', '2043-01-01', 1),
(2, 2, 2, 30000, 4.0, 2, '2024-06-01', '2029-06-01', 2),
(3, 3, 3, 10000, 5.5, 1, '2024-11-01', NULL, 3);

-- Populating the `loan_payment_schedule` table
INSERT INTO loan_payment_schedule (schedule_id, loan_id, due_date, amount_due, status, bank_id) VALUES
(1, 1, '2024-12-01', 1500.00, 0, 1),
(2, 2, '2024-12-15', 500.00, 0, 2),
(3, 3, '2024-12-20', 200.00, 1, 3);

-- Populating the `loan_payment` table
INSERT INTO loan_payment (payment_id, schedule_id, amount_paid, payment_date, bank_id) VALUES
(1, 3, 200.00, '2024-11-28 10:00:00', 3);
