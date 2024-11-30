package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class account_type {
    public int account_type_id; // Primary Key
    public String type_name; // Account type name (e.g., Savings, Checking, Business)
    public String description; // Detailed description (Optional)
    
    public account_type(int account_type_id, String type_name, String description) {
        this.account_type_id = account_type_id;
        this.type_name = type_name;
        this.description = description;
    }

     public static account_type getByID(Connection connection, int id) {
        account_type instance = null;
        String query = "SELECT * FROM account_type WHERE account_type_id = ?";

        try  {
            connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Set the parameter for userId
            preparedStatement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a User object
                    instance = new account_type(
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
        return "account_type [account_type_id=" + account_type_id + ", type_name=" + type_name + ", description="
                + description + "]";
    }
}
