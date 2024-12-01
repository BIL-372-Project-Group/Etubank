function generateInsertStatements() {
  // Define possible attribute values for each table

    multiplier = 1;

  // Banks table data
  const banks = [
    { bank_id: 1, name: 'First National Bank', address: '123 Elm Street, Cityville', contact_number: '123-456-7890', branch_code: 'FNB123' },
    { bank_id: 2, name: 'Global Trust Bank', address: '456 Maple Avenue, Townsville', contact_number: '987-654-3210', branch_code: 'GTB456' }
  ];

  // Customers table data
  const firstNames = ['John', 'Jane', 'Alice', 'Bob'];
  const lastNames = ['Doe', 'Smith', 'Johnson', 'Brown'];
  const nationalities = ['USA', 'UK', 'Canada', 'Germany'];
  const emails = ['example1@example.com', 'example2@example.com', 'example3@example.com', 'example4@example.com'];
  const cities = ['Cityville', 'Townsville', 'Metropolis', 'Villagetown'];
  const states = ['CA', 'TX', 'NY', 'FL'];
  const countries = ['USA', 'Canada', 'UK', 'Germany'];
  const passwords = ['hashed_password_1', 'hashed_password_2', 'hashed_password_3', 'hashed_password_4'];

  // Generate combinations for customers
  const customerCombinations = cartesianProduct([firstNames, lastNames, nationalities, emails, cities, states, countries, passwords]);

  // Create sample customer data with all combinations and a bank reference
  const customers = customerCombinations.map((combination, index) => {
    return {
      customer_id: index + 1,
      bank_id: (index % banks.length) + 1, // Alternates between banks
      first_name: combination[0],
      last_name: combination[1],
      date_of_birth: '1990-01-01', // Example fixed date for simplicity
      nationality: combination[2],
      nin: `NIN${(index + 1).toString().padStart(5, '0')}`,
      last_login_date: `2024-11-30 08:15:${(index % 60).toString().padStart(2, '0')}`,
      email: combination[3],
      phone_number: `123-456-${(index + 1000).toString().padStart(4, '0')}`,
      address: `${combination[4]} Street`,
      city: combination[4],
      state: combination[5],
      postal_code: `${(index + 1000).toString().padStart(5, '0')}`,
      country_of_residence: combination[6],
      password: combination[7]
    };
  });

  // Account types and statuses data
  const accountTypes = [
    { account_type_id: 1, type_name: 'Checking', description: 'Regular checking account' },
    { account_type_id: 2, type_name: 'Savings', description: 'High-yield savings account' }
  ];

  const accountStatuses = [
    { account_status_id: 1, type_name: 'Active', description: 'Account is active' },
    { account_status_id: 2, type_name: 'Inactive', description: 'Account is inactive' }
  ];

  // Generate sample account data
  const accounts = [];
  for (let i = 0; i < multiplier * 1; i++) {
    accounts.push({
      account_id: i + 1,
      customer_id: (i % customers.length) + 1,
      iban: `DE44 1234 5678 9123 4567 89${i}`,
      account_number: `${100000 + i}`,
      account_status_id: (i % accountStatuses.length) + 1,
      account_type_id: (i % accountTypes.length) + 1,
      date_opened: `2022-01-${(i % 28) + 1}`,
      last_activity_date: `2024-11-30 08:15:${(i % 60).toString().padStart(2, '0')}`,
      balance: (i + 1) * 1000.00,
      currency: 'USD',
      bank_id: (i % banks.length) + 1
    });
  }

  // Card types data
  const cardTypes = [
    { card_type_id: 1, type_name: 'Credit', description: 'Credit card' },
    { card_type_id: 2, type_name: 'Debit', description: 'Debit card' }
  ];

  // Generate sample card data
  const cards = [];
  for (let i = 0; i < 50000; i++) {
    cards.push({
      card_id: i + 1,
      account_id: (i % accounts.length) + 1,
      card_type_id: (i % cardTypes.length) + 1,
      card_number: `1234567890123456${i}`,
      card_limit: (i + 1) * 5000.00,
      expiry_date: `2026-12-${(i % 28) + 1}`,
      cvv: `${100 + i}`,
      bank_id: (i % banks.length) + 1
    });
  }

  // Transaction types data
  const transactionTypes = [
    { transaction_type_id: 1, type_name: 'Withdrawal', description: 'Money withdrawal' },
    { transaction_type_id: 2, type_name: 'Deposit', description: 'Money deposit' },
    { transaction_type_id: 3, type_name: 'Transfer', description: 'Money transfer' }
  ];

  // Generate sample transaction data
  const transactions = [];
  for (let i = 0; i < 1000000; i++) {
    transactions.push({
      transaction_id: i + 1,
      transaction_type_id: (i % transactionTypes.length) + 1,
      sender_id: (i % accounts.length) + 1,
      recipient_id: ((i + 1) % accounts.length) + 1,
      amount: (i + 1) * 100.00,
      transaction_date: `2024-11-30 08:15:${(i % 60).toString().padStart(2, '0')}`,
      bank_id: (i % banks.length) + 1
    });
  }

  // Loan types and statuses data
  const loanTypes = [
    { loan_type_id: 1, type_name: 'Mortgage', description: 'Home loan' },
    { loan_type_id: 2, type_name: 'Personal', description: 'Personal loan' }
  ];

  const loanStatuses = [
    { loan_status_id: 1, type_name: 'Active', description: 'Loan is active' },
    { loan_status_id: 2, type_name: 'Closed', description: 'Loan is closed' }
  ];

  // Generate sample loan data
  const loans = [];
  for (let i = 0; i < 50000; i++) {
    loans.push({
      loan_id: i + 1,
      loan_type_id: (i % loanTypes.length) + 1,
      customer_id: (i % customers.length) + 1,
      principal_amount: (i + 1) * 10000,
      interest_rate: 5.0,
      loan_status_id: (i % loanStatuses.length) + 1,
      start_date: `2022-01-${(i % 28) + 1}`,
      end_date: `2025-12-${(i % 28) + 1}`,
      bank_id: (i % banks.length) + 1
    });
  }

  // Loan payment schedules data
  const loanPaymentSchedules = [];
  for (let i = 0; i < 5000000; i++) {
    loanPaymentSchedules.push({
      schedule_id: i + 1,
      loan_id: (i % loans.length) + 1,
      due_date: `2024-12-${(i % 28) + 1}`,
      amount_due: (i + 1) * 500,
      status: i % 2 === 0, // Alternates between true and false
      bank_id: (i % banks.length) + 1
    });
  }

  // Loan payments data
  const loanPayments = [];
  for (let i = 0; i < 500000; i++) {
    loanPayments.push({
      payment_id: i + 1,
      schedule_id: (i % loanPaymentSchedules.length) + 1,
      amount_paid: (i + 1) * 500,
      payment_date: `2024-11-30 08:15:${(i % 60).toString().padStart(2, '0')}`,
      bank_id: (i % banks.length) + 1
    });
  }

  // Function to generate SQL insert statements
  function generateSQLInsert(tableName, data) {
    const columns = Object.keys(data[0]).join(', ');
    const values = data.map(row => {
      const rowValues = Object.values(row).map(value => {
        if (typeof value === 'string') {
          return `'${value.replace(/'/g, "''")}'`; // Escape single quotes
        } else if (value === null) {
          return 'NULL';
        } else {
          return value;
        }
      }).join(', ');
      return `(${rowValues})`;
    }).join(',\n');

    return `INSERT INTO ${tableName} (${columns}) VALUES\n${values};\n`;
  }

  // Print SQL insert statements for each table
  console.log(generateSQLInsert('bank', banks));
  console.log(generateSQLInsert('customer', customers));
  console.log(generateSQLInsert('account', accounts));
  console.log(generateSQLInsert('card', cards));
  console.log(generateSQLInsert('transaction', transactions));
  console.log(generateSQLInsert('loan', loans));
  console.log(generateSQLInsert('loan_payment_schedule', loanPaymentSchedules));
  console.log(generateSQLInsert('loan_payment', loanPayments));
}

function cartesianProduct(arrays) {
  return arrays.reduce((acc, array) => 
    acc.flatMap(val => 
      array.map(el => 
        [...val, el]
      )
    ), 
    [[]]
  );
}

// Run the script
generateInsertStatements();
