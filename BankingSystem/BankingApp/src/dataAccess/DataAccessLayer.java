package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//interaction with MYSQL database.

public class DataAccessLayer {

    //db configuration
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/etubank";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "8%g4J0Q=r/Yg2X80{!z>";


    public static customer createSession(String email, String password) {
        try {
            System.out.println("Attempting to connect to the database...");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection established successfully!");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE email = ? AND password = ?"
            );

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // Execute the query and store the result in the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the query returned any data
            if (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                customer temp = customer.getByID(connection, customerId);
                return temp;
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred.");
            e.printStackTrace();
        }

        // Not a valid user
        return null;
    }

    public static account getAccountByIBAN(String transferredUserIBAN) {
        account accountInstance = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM account WHERE iban = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, transferredUserIBAN);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        accountInstance = new account(
                                resultSet.getInt("account_id"),
                                resultSet.getInt("customer_id"),
                                resultSet.getString("iban"),
                                resultSet.getString("account_number"),
                                resultSet.getInt("account_status_id"),
                                resultSet.getInt("account_type_id"),
                                resultSet.getDate("date_opened"),
                                resultSet.getDate("last_activity_date"),
                                resultSet.getBigDecimal("balance"),
                                resultSet.getString("currency"),
                                transaction_history.getByAccountID(connection, resultSet.getInt("account_id")),
                                card.getByAccountID(connection, resultSet.getInt("account_id")),
                                account_status.getByID(connection, resultSet.getInt("account_status_id")),
                                account_type.getByID(connection, resultSet.getInt("account_type_id"))
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountInstance;
    }

}
