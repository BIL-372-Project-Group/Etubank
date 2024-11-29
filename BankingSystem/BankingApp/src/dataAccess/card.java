package dataAccess;

import java.util.Date;
import java.sql.Connection;

public class card {
    public int card_id; // Primary Key
    public int account_id; // Foreign Key (references Account.account_id)
    public int card_type_id; // Foreign Key (references Card_type.card_type_id)
    public String card_number; // Unique card number
    public double card_limit; // Credit/debit limit
    public Date expiry_date; // Expiry date
    public int cvv; // Security code

    public card_type type;

    public card(int card_id, int account_id, int card_type_id, String card_number, double card_limit, Date expiry_date,
            int cvv, card_type type) {
        this.card_id = card_id;
        this.account_id = account_id;
        this.card_type_id = card_type_id;
        this.card_number = card_number;
        this.card_limit = card_limit;
        this.expiry_date = expiry_date;
        this.cvv = cvv;
        this.type = type;
    }

    @Override
    public String toString() {
        return "card [card_id=" + card_id + ", account_id=" + account_id + ", card_type_id=" + card_type_id
                + ", card_number=" + card_number + ", card_limit=" + card_limit + ", expiry_date=" + expiry_date
                + ", cvv=" + cvv + ", type=" + type + "]";
    }
}
