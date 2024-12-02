-- Sample data for account_status
INSERT INTO account_status (account_status_id, type_name, description) VALUES
(1, 'Active', 'The account is active and can be used normally.'),
(2, 'Inactive', 'The account is inactive and cannot be used.'),
(3, 'Suspended', 'The account is suspended due to policy violations or other reasons.'),
(4, 'Closed', 'The account has been permanently closed.');

-- Sample data for account_type
INSERT INTO account_type (account_type_id, type_name, description) VALUES
(1, 'Savings', 'A standard savings account with interest.'),
(2, 'Checking', 'A basic checking account for daily transactions.'),
(3, 'Business', 'An account designed for business purposes.'),
(4, 'Joint', 'An account shared between multiple individuals.');

-- Sample data for card_type
INSERT INTO card_type (card_type_id, type_name, description) VALUES
(1, 'Debit', 'A debit card linked directly to a checking account.'),
(2, 'Credit', 'A credit card with a predefined credit limit.'),
(3, 'Prepaid', 'A prepaid card loaded with a specific amount of money.'),
(4, 'Virtual', 'A virtual card used for online transactions.');

-- Sample data for transaction_type
INSERT INTO transaction_type (transaction_type_id, type_name, description) VALUES
(1, 'Deposit', 'A transaction where money is added to an account.'),
(2, 'Withdrawal', 'A transaction where money is removed from an account.'),
(3, 'Transfer', 'A transaction where money is moved between accounts.'),
(4, 'Payment', 'A transaction for paying bills or other services.');

-- Sample data for loan_status
INSERT INTO loan_status (loan_status_id, type_name, description) VALUES
(1, 'Pending', 'Loan application is pending review.'),
(2, 'Approved', 'Loan application has been approved.'),
(3, 'Rejected', 'Loan application has been rejected.'),
(4, 'Active', 'Loan is active and payments are ongoing.'),
(5, 'Closed', 'Loan has been fully paid off.');

-- Sample data for loan_type
INSERT INTO loan_type (loan_type_id, type_name, description) VALUES
(1, 'Personal Loan', 'A loan taken out for personal use.'),
(2, 'Mortgage', 'A loan for purchasing or refinancing real estate.'),
(3, 'Auto Loan', 'A loan specifically for purchasing a vehicle.'),
(4, 'Student Loan', 'A loan designed to finance educational expenses.'),
(5, 'Business Loan', 'A loan taken out for business purposes.');

INSERT INTO bank (bank_id, name, address, contact_number, branch_code) VALUES
(1, 'Türkiye İş Bankası', 'İstiklal Caddesi 10, Beyoğlu, İstanbul', '+90-212-555-0101', 'TIB010'),
(2, 'Garanti BBVA', 'Cumhuriyet Mahallesi, Şişli, İstanbul', '+90-212-444-0303', 'GBB444'),
(3, 'Ziraat Bankası', 'Atatürk Bulvarı 5, Altındağ, Ankara', '+90-312-444-0000', 'ZBK005'),
(4, 'Akbank', 'Mecidiyeköy Mahallesi, Şişli, İstanbul', '+90-212-444-2525', 'AKB252'),
(5, 'Yapı Kredi Bankası', 'Levent Çarşı, Beşiktaş, İstanbul', '+90-212-444-1111', 'YKB111');