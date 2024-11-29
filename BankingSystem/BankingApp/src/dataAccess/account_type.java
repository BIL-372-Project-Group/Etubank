package dataAccess;

import java.sql.Connection;

public class account_type {
    int account_type_id; // Primary Key
    String type_name; // Account type name (e.g., Savings, Checking, Business)
    String description; // Detailed description (Optional)
    
    public account_type(int account_type_id, String type_name, String description) {
        this.account_type_id = account_type_id;
        this.type_name = type_name;
        this.description = description;
    }
}
