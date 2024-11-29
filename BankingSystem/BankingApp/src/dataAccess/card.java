package dataAccess;

import java.util.Date;
import java.sql.Connection;

public class card {
    int card_id; // Primary Key
    int account_id; // Foreign Key (references Account.account_id)
    int card_type_id; // Foreign Key (references Card_type.card_type_id)
    String card_number; // Unique card number
    double card_limit; // Credit/debit limit
    Date expiry_date; // Expiry date
    int cvv; // Security code

    card_type type;

    public card(Connection c, int id) {
        this.card_id = id;
        this.account_id = account_id;
        this.card_type_id = card_type_id;
        this.card_number = card_number;
        this.card_limit = card_limit;
        this.expiry_date = expiry_date;
        this.cvv = cvv;
        this.type = type;
    }
}
