package dataAccess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;

public class transaction_history {
    int history_id; // Primary Key
    int account_id; // Foreign Key (references account.account_id)
    int transaction_id; // Foreign Key (references transaction.transaction_id)
    Date transaction_date; // Date of the transaction
    BigDecimal balance_after_transaction; // Account balance after the transaction

    ArrayList<transaction> transactions;

    public transaction_history(int history_id, int account_id, int transaction_id, Date transaction_date,
            BigDecimal balance_after_transaction, ArrayList<transaction> transactions) {
        this.history_id = history_id;
        this.account_id = account_id;
        this.transaction_id = transaction_id;
        this.transaction_date = transaction_date;
        this.balance_after_transaction = balance_after_transaction;
        this.transactions = transactions;
    }
}
