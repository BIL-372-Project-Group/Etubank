-- Table for banks
CREATE TABLE bank (
    bank_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    contact_number VARCHAR(50),
    branch_code VARCHAR(50)
);

-- Table for customers
CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    bank_id INT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    nationality VARCHAR(100),
    nin VARCHAR(50),  -- National Identification Number
    last_login_date DATETIME,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50),
    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(50),
    country_of_residence VARCHAR(100),
    password VARCHAR(255) NOT NULL, -- Assuming hashed passwords
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);

-- Table for account statuses
CREATE TABLE account_status (
    account_status_id INT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Table for account types
CREATE TABLE account_type (
    account_type_id INT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Table for accounts
CREATE TABLE account (
    account_id INT PRIMARY KEY,
    customer_id INT,
    iban VARCHAR(34) NOT NULL, -- IBAN format length
    account_number VARCHAR(20) NOT NULL UNIQUE,
    account_status_id INT,
    account_type_id INT,
    date_opened DATE NOT NULL,
    last_activity_date DATETIME,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    currency VARCHAR(10),
    bank_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (account_status_id) REFERENCES account_status(account_status_id),
    FOREIGN KEY (account_type_id) REFERENCES account_type(account_type_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);

-- Table for card types
CREATE TABLE card_type (
    card_type_id INT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Table for cards
CREATE TABLE card (
    card_id INT PRIMARY KEY,
    account_id INT,
    card_type_id INT,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    card_limit DECIMAL(15, 2),
    expiry_date DATE NOT NULL,
    cvv VARCHAR(3),
    bank_id INT,
    FOREIGN KEY (account_id) REFERENCES account(account_id),
    FOREIGN KEY (card_type_id) REFERENCES card_type(card_type_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);

-- Table for transaction types
CREATE TABLE transaction_type (
    transaction_type_id INT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Table for transactions
CREATE TABLE transaction (
    transaction_id INT PRIMARY KEY,
    transaction_type_id INT,
    sender_id INT,
    recipient_id INT,
    amount DECIMAL(15, 2) NOT NULL CHECK (amount > 0),
    transaction_date DATETIME NOT NULL,
    bank_id INT,
    FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(transaction_type_id),
    FOREIGN KEY (sender_id) REFERENCES account(account_id),
    FOREIGN KEY (recipient_id) REFERENCES account(account_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);

-- Table for transaction history
CREATE TABLE transaction_history (
    history_id INT PRIMARY KEY,
    account_id INT,
    transaction_id INT,
    transaction_date DATETIME NOT NULL,
    balance_after_transaction DECIMAL(15, 2),
    bank_id INT,
    FOREIGN KEY (account_id) REFERENCES account(account_id),
    FOREIGN KEY (transaction_id) REFERENCES transaction(transaction_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);

-- Table for loan statuses
CREATE TABLE loan_status (
    loan_status_id INT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Table for loan types
CREATE TABLE loan_type (
    loan_type_id INT PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Table for loans
CREATE TABLE loan (
    loan_id INT PRIMARY KEY,
    loan_type_id INT,
    customer_id INT,
    principal_amount INT NOT NULL CHECK (principal_amount > 0),
    interest_rate DECIMAL(5, 2) NOT NULL CHECK (interest_rate > 0),
    loan_status_id INT,
    start_date DATE NOT NULL,
    end_date DATE,
    bank_id INT,
    FOREIGN KEY (loan_type_id) REFERENCES loan_type(loan_type_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (loan_status_id) REFERENCES loan_status(loan_status_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);

-- Table for loan payment schedules
CREATE TABLE loan_payment_schedule (
    schedule_id INT PRIMARY KEY,
    loan_id INT,
    due_date DATE NOT NULL,
    amount_due DECIMAL(15, 2) NOT NULL CHECK (amount_due > 0),
    status BOOLEAN NOT NULL, -- Paid or Unpaid (1 or 0)
    bank_id INT,
    FOREIGN KEY (loan_id) REFERENCES loan(loan_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);

-- Table for loan payments
CREATE TABLE loan_payment (
    payment_id INT PRIMARY KEY,
    schedule_id INT,
    amount_paid DECIMAL(15, 2) NOT NULL CHECK (amount_paid > 0),
    payment_date DATETIME NOT NULL,
    bank_id INT,
    FOREIGN KEY (schedule_id) REFERENCES loan_payment_schedule(schedule_id),
    FOREIGN KEY (bank_id) REFERENCES bank(bank_id)
);