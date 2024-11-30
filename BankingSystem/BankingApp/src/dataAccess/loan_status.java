package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static loan_status getByID(Connection connection, int id) {

        loan_status instance = null;

        String query = "SELECT * FROM loan_status WHERE loan_status_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for userId
            preparedStatement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set
                    instance = new loan_status(
                            id,
                            resultSet.getString("type_name"),
                            resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return instance;
    }
}
