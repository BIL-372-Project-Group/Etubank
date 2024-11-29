package dataAccess;

import java.sql.Connection;

public class card_type {
    int card_type_id; // Primary Key
    String type_name; // Card type name (e.g., Credit, Debit)
    String description; // Detailed description (Optional)
    
    public card_type(int card_type_id, String type_name, String description) {
        this.card_type_id = card_type_id;
        this.type_name = type_name;
        this.description = description;
    }
}
