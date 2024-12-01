package dataAccess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class transaction_history {
    public int history_id; // Primary Key
    public int account_id; // Foreign Key (references account.account_id)
    public int transaction_id; // Foreign Key (references transaction.transaction_id)
    public Date transaction_date; // Date of the transaction
    public BigDecimal balance_after_transaction; // Account balance after the transaction

    transaction transactionInstance;

    public transaction_history(int history_id, int account_id, int transaction_id, Date transaction_date,
            BigDecimal balance_after_transaction, transaction transactionInstance) {
        this.history_id = history_id;
        this.account_id = account_id;
        this.transaction_id = transaction_id;
        this.transaction_date = transaction_date;
        this.balance_after_transaction = balance_after_transaction;
        this.transactionInstance = transactionInstance;
    }

    @Override
    public String toString() {
        return "transaction_history [history_id=" + history_id + ", account_id=" + account_id + ", transaction_id="
                + transaction_id + ", transaction_date=" + transaction_date + ", balance_after_transaction="
                + balance_after_transaction + ", transaction=" + transactionInstance + "]";
    }

    public static ArrayList<transaction_history> getByAccountID(Connection connection, int aid) {

        ArrayList<transaction_history> list = new ArrayList<transaction_history>();

        String query = "SELECT * FROM transaction_history WHERE account_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Set the parameter for userId
            preparedStatement.setInt(1, aid);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Map the result set to a User object
                    int id = resultSet.getInt("history_id");
                    list.add(
                        new transaction_history(
                        id,
                        aid,
                        resultSet.getInt("transaction_id"),
                        resultSet.getDate("transaction_date"),
                        resultSet.getBigDecimal("balance_after_transaction"),
                        transaction.getByID(connection, resultSet.getInt("transaction_id"))
                        )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return list;
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
}
