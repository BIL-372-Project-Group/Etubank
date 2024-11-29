package dataAccess;

import java.sql.Connection;

public class loan_status {
    public int loan_status_id; // Primary Key
    public String type_name; // Name of the loan status (e.g., Active, Closed)
    public String description; // Detailed description (Optional)
    
    public loan_status(int loan_status_id, String type_name, String description) {
        this.loan_status_id = loan_status_id;
        this.type_name = type_name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "loan_status [loan_status_id=" + loan_status_id + ", type_name=" + type_name + ", description="
                + description + "]";
    }
}
