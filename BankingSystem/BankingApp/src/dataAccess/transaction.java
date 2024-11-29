package dataAccess;

import java.sql.Connection;

// Transaction data.

import java.math.BigDecimal;
import java.sql.Date;

public class transaction {
    public int transaction_id;
    public int transaction_type_id;
    public int sender_id;
    public int recipient_id;
    public BigDecimal amount;
    public Date transaction_date;
    
    public transaction(int transaction_id, int transaction_type_id, int sender_id, int recipient_id, BigDecimal amount,
            Date transaction_date) {
        this.transaction_id = transaction_id;
        this.transaction_type_id = transaction_type_id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.amount = amount;
        this.transaction_date = transaction_date;
    }

    @Override
    public String toString() {
        return "transaction [transaction_id=" + transaction_id + ", transaction_type_id=" + transaction_type_id
                + ", sender_id=" + sender_id + ", recipient_id=" + recipient_id + ", amount=" + amount
                + ", transaction_date=" + transaction_date + "]";
    }
}
