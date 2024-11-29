package dataAccess;

import java.sql.Connection;

// Transaction data.

import java.math.BigDecimal;
import java.sql.Date;

public class transaction {
    int transaction_id;
    int transaction_type_id;
    int sender_id;
    int recipient_id;
    BigDecimal amount;
    Date transaction_date;
    
    public transaction(int transaction_id, int transaction_type_id, int sender_id, int recipient_id, BigDecimal amount,
            Date transaction_date) {
        this.transaction_id = transaction_id;
        this.transaction_type_id = transaction_type_id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.amount = amount;
        this.transaction_date = transaction_date;
    }
}
