package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class loan_type {
    public int loan_type_id; // Primary Key
    public String type_name; // Type of loan (e.g., Home, Car)
    public String description; // Detailed description

    public loan_type(int loan_type_id, String type_name, String description) {
        this.loan_type_id = loan_type_id;
        this.type_name = type_name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "loan_type [loan_type_id=" + loan_type_id + ", type_name=" + type_name + ", description=" + description
                + "]";
    }

    public static loan_type getByLoanID(Connection connection, int lid) {

        loan_type instance = null;

        String query = "SELECT * FROM loan_type WHERE loan_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for userId
            preparedStatement.setInt(1, lid);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a User object
                    int id = resultSet.getInt("loan_type_id");
                    instance = new loan_type(
                            id,
                            resultSet.getString("name"),
                            resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return instance;
    }
}
