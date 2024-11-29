package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class account_status {
    int account_status_id; // Primary Key
    String type_name; // Status type (e.g., Active, Closed)
    String description; // Detailed description (Optional)

public static account_status getByID(Connection connection, int id) {
        customer instance = null;
        String query = "SELECT first_name, last_name, date_of_birth, nationality, nin, " +
                "last_login_date, email, phone_number, address, city, state, " +
                "postal_code, country_of_residence, password FROM customer WHERE customer_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Set the parameter for userId
            preparedStatement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a User object
                    instance = new customer(
                            id,
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getString("nationality"),
                            resultSet.getString("nin"),
                            resultSet.getDate("last_login_date"),
                            resultSet.getString("email"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("address"),
                            resultSet.getString("city"),
                            resultSet.getString("state"),
                            resultSet.getString("postal_code"),
                            resultSet.getString("country_of_residence"),
                            resultSet.getString("password"),
                            null,
                            null
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return instance;
    }
}
