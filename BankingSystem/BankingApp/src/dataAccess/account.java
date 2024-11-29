package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;

public class account {
    int account_id; // Primary Key
    int customer_id; // Foreign Key (references Customer.customer_id)
    String iban; // International Bank Account Number
    String account_number; // Unique account number for transactions
    int account_status_id; // Foreign Key (references Account_status.account_status_id)
    int account_type_id; // Foreign Key (references Account_type.account_type_id)
    Date date_opened; // Date account was opened
    Date last_activity_date; // Last activity date
    double balance; // Current account balance
    String currency; // Currency type (e.g., USD, EUR)

    transaction_history transactionHistory;
    ArrayList<card> cards;
    account_status status;
    account_type type;
    
    public account(int account_id, int customer_id, String iban, String account_number, int account_status_id,
            int account_type_id, Date date_opened, Date last_activity_date, double balance, String currency,
            transaction_history transactionHistory, ArrayList<card> cards, account_status status, account_type type) {
        this.account_id = account_id;
        this.customer_id = customer_id;
        this.iban = iban;
        this.account_number = account_number;
        this.account_status_id = account_status_id;
        this.account_type_id = account_type_id;
        this.date_opened = date_opened;
        this.last_activity_date = last_activity_date;
        this.balance = balance;
        this.currency = currency;
        this.transactionHistory = transactionHistory;
        this.cards = cards;
        this.status = status;
        this.type = type;
    }
}
