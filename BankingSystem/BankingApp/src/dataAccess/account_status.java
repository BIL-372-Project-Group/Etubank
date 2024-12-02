package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class account_status {
    public int account_status_id; // Primary Key
    public String type_name; // Status type (e.g., Active, Closed)
    public String description; // Detailed description (Optional)
    
    public account_status(int account_status_id, String type_name, String description) {
        this.account_status_id = account_status_id;
        this.type_name = type_name;
        this.description = description;
    }

    public static account_status getByID(Connection connection, int id) {
        account_status instance = null;
        String query = "SELECT * FROM account_status WHERE account_status_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Set the parameter for userId
            preparedStatement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a User object
                    instance = new account_status(
                            id,
                            resultSet.getString("type_name"),
                            resultSet.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return instance;
    }

    @Override
    public String toString() {
        return "account_status [account_status_id=" + account_status_id + ", type_name=" + type_name + ", description="
                + description + "]";
    }
}
