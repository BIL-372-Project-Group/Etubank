package dataAccess;

import java.sql.Connection;

public class transaction_type {
    int transaction_type_id; // Primary Key
    String type_name; // Name of the transaction type
    String description; // Detailed description (Optional)

    transaction_type type;

    public transaction_type(int transaction_type_id, String type_name, String description, transaction_type type) {
        this.transaction_type_id = transaction_type_id;
        this.type_name = type_name;
        this.description = description;
        this.type = type;
    }
}
