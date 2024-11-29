package dataAccess;

import java.sql.Connection;

public class loan_type {
    int loan_type_id; // Primary Key
    String type_name; // Type of loan (e.g., Home, Car)
    String description; // Detailed description
    
    public loan_type(int loan_type_id, String type_name, String description) {
        this.loan_type_id = loan_type_id;
        this.type_name = type_name;
        this.description = description;
    }
}
