package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class account {
    public int account_id; // Primary Key
    public int customer_id; // Foreign Key (references Customer.customer_id)
    public String iban; // International Bank Account Number
    public String account_number; // Unique account number for transactions
    public int account_status_id; // Foreign Key (references Account_status.account_status_id)
    public int account_type_id; // Foreign Key (references Account_type.account_type_id)
    public Date date_opened; // Date account was opened
    public Date last_activity_date; // Last activity date
    public BigDecimal balance; // Current account balance
    public String currency; // Currency type (e.g., USD, EUR)

    public ArrayList<transaction_history> transactionHistory;
    public ArrayList<card> cards;
    public account_status status;
    public account_type type;

    public account(int account_id, int customer_id, String iban, String account_number, int account_status_id,
            int account_type_id, Date date_opened, Date last_activity_date, BigDecimal balance, String currency,
            ArrayList<transaction_history> transactionHistory, ArrayList<card> cards, account_status status, account_type type) {
        this.account_id = account_id;
        this.customer_id = customer_id;
        this.iban = iban;
        this.account_number = account_number;
        this.account_status_id = account_status_id;
        this.account_type_id = account_type_id;
        this.date_opened = date_opened;
        this.last_activity_date = last_activity_date;
        this.balance = balance;
        this.currency = currency;
        this.transactionHistory = transactionHistory;
        this.cards = cards;
        this.status = status;
        this.type = type;
    }

    public static ArrayList<account> getByCustomerID(Connection connection, int cid) {

        ArrayList<account> list = new ArrayList<account>();

        String query = "SELECT * FROM account WHERE customer_id = ?";

        try  {
            connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameter for userId
            preparedStatement.setInt(1, cid);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Map the result set to a User object
                    int id = resultSet.getInt("account_id");
                    list.add(
                        new account(
                            id,
                            resultSet.getInt("customer_id"),
                            resultSet.getString("iban"),
                            resultSet.getString("account_number"),
                            resultSet.getInt("account_status_id"),
                            resultSet.getInt("account_type_id"),
                            resultSet.getDate("date_opened"),
                            resultSet.getDate("last_activity_date"),
                            resultSet.getBigDecimal("balance"),
                            resultSet.getString("currency"),
                            transaction_history.getByAccountID(connection, id),
                            card.getByAccountID(connection, id),
                            account_status.getByID(connection,id),
                            account_type.getByID(connection, id)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return list;
    }

    @Override
    public String toString() {
        return "account [account_id=" + account_id + ", customer_id=" + customer_id + ", iban=" + iban
                + ", account_number=" + account_number + ", account_status_id=" + account_status_id
                + ", account_type_id=" + account_type_id + ", date_opened=" + date_opened + ", last_activity_date="
                + last_activity_date + ", balance=" + balance + ", currency=" + currency + ", transactionHistory="
                + transactionHistory + ", cards=" + cards + ", status=" + status + ", type=" + type + "]";
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void handleTransaction(String transactionType, BigDecimal amount) {
        if (transactionType.equalsIgnoreCase("Deposit")) {
            balance = balance.add(amount);
        } else if (transactionType.equalsIgnoreCase("Withdraw")) {
            balance = balance.subtract(amount);
        }
        updateBalanceInDatabase();
    }

    public void handleTransfer(account recipientAccount, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }
        balance = balance.subtract(amount);
        recipientAccount.balance = recipientAccount.balance.add(amount);
        updateBalanceInDatabase();
        recipientAccount.updateBalanceInDatabase();
    }
    

    private void updateBalanceInDatabase() {
        try (Connection connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD)) {
            String query = "UPDATE account SET balance = ? WHERE account_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setBigDecimal(1, balance);
                preparedStatement.setInt(2, account_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

}
