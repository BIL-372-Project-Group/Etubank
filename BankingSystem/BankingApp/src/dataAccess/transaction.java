package dataAccess;

import java.sql.Connection;

// Transaction data.

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<transaction> getByAccountID(Connection connection, int accountId) {
    ArrayList<transaction> transactions = new ArrayList<>();

    String query = "SELECT * FROM transaction WHERE sender_id = ? OR recipient_id = ?";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, accountId);
        preparedStatement.setInt(2, accountId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                transaction instance = new transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getInt("transaction_type_id"),
                        resultSet.getInt("sender_id"),
                        resultSet.getInt("recipient_id"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getDate("transaction_date")
                );
                transactions.add(instance);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return transactions;
}

public String getTRANSACTION_TYPE_NAME(int transaction_id) {
    int transaction_type_id = 0;
        try (Connection connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD)) {
            String query = "SELECT transaction_type_id FROM transaction WHERE transaction_id = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, transaction_id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        transaction_type_id = resultSet.getInt("transaction_type_id");
                        String query2 = "SELECT type_name FROM transaction_type WHERE transaction_type_id = ? ";
                        try (PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
                            preparedStatement2.setInt(1, transaction_type_id);
                            try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
                                if (resultSet2.next()) {
                                    return resultSet2.getString("type_name");
                                }
                            }
                        }
                        return "returned empty";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
    return "returned empty";
}


public BigDecimal getTRANSACTION_AMOUNT(int transaction_id) {
    BigDecimal transactionAmount = new BigDecimal(0);

    try (Connection connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD)) {
        String query = "SELECT amount FROM transaction WHERE transaction_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, transaction_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    transactionAmount = resultSet.getBigDecimal("amount");
                    return transactionAmount;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return transactionAmount;
}

public Date getTRANSACTION_DATE() {
    return transaction_date;
}

    
    public static void addTransactionToDatabase(Connection connection, int transactionTypeId, int senderId, int recipientId, BigDecimal amount) throws SQLException {
        String query = "INSERT INTO transaction (transaction_id, transaction_type_id, sender_id, recipient_id, amount, transaction_date) VALUES (?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, generateTransactionId(connection));
            preparedStatement.setInt(2, transactionTypeId);
            preparedStatement.setInt(3, senderId);
            preparedStatement.setInt(4, recipientId);
            preparedStatement.setBigDecimal(5, amount);
            preparedStatement.executeUpdate();
            
        }
    }

    public static int generateTransactionId(Connection connection) throws SQLException {
        String query = "SELECT MAX(transaction_id) AS max_id FROM transaction";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("max_id") + 1;
            } else {
                return 1; // Start with 1 if there are no transactions
            }
        }
    }
    
}
