package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class card_type {
    public int card_type_id; // Primary Key
    public String type_name; // Card type name (e.g., Credit, Debit)
    public String description; // Detailed description (Optional)

    public card_type(int card_type_id, String type_name, String description) {
        this.card_type_id = card_type_id;
        this.type_name = type_name;
        this.description = description;
    }

    public static card_type getByID(Connection connection, int id) {
        card_type instance = null;
        String query = "SELECT * FROM card_type WHERE card_type_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for userId
            preparedStatement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a User object
                    instance = new card_type(
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

    @Override
    public String toString() {
        return "card_type [card_type_id=" + card_type_id + ", type_name=" + type_name + ", description=" + description
                + "]";
    }
}
