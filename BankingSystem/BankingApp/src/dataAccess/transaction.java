package dataAccess;

import java.sql.Connection;

// Transaction data.

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class transaction {
    public int transaction_id;
    public int transaction_type_id;
    public int sender_id;
    public int recipient_id;
    public BigDecimal amount;
    public Date transaction_date;
    
    public transaction(int transaction_id, int transaction_type_id, int sender_id, int recipient_id, BigDecimal amount,
            Date transaction_date) {
        this.transaction_id = transaction_id;
        this.transaction_type_id = transaction_type_id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.amount = amount;
        this.transaction_date = transaction_date;
    }

    @Override
    public String toString() {
        return "transaction [transaction_id=" + transaction_id + ", transaction_type_id=" + transaction_type_id
                + ", sender_id=" + sender_id + ", recipient_id=" + recipient_id + ", amount=" + amount
                + ", transaction_date=" + transaction_date + "]";
    }

    public static transaction getByID(Connection connection, int id) {

        transaction instance = null;

        String query = "SELECT * FROM transaction WHERE transaction_id = ?";

        try  {
            
            connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Set the parameter for userId
            preparedStatement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a User object
                    instance = new transaction(
                            id,
                            resultSet.getInt("transaction_type_id"),
                            resultSet.getInt("sender_id"),
                            resultSet.getInt("recipient_id"),
                            resultSet.getBigDecimal("amount"),
                            resultSet.getDate("transaction_date")
                            );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return instance;
    }
}
