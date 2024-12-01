package dataAccess;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class customer {
    public int customer_id;
    public String first_name;
    public String last_name;
    public Date date_of_birth;
    public String nationality;
    public String nin;
    public Date last_login_date;
    public String email;
    public String phone_number;
    public String address;
    public String city;
    public String state;
    public String postal_code;
    public String country_of_residence;
    public String password;

    public static Connection storedConnection;

    public ArrayList<account> accounts;
    public ArrayList<loan> loans;

    public customer(int customer_id, String first_name, String last_name, Date date_of_birth, String nationality,
            String nin, Date last_login_date, String email, String phone_number, String address, String city,
            String state, String postal_code, String country_of_residence, String password, ArrayList<account> accounts,
            ArrayList<loan> loans) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.nationality = nationality;
        this.nin = nin;
        this.last_login_date = last_login_date;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.country_of_residence = country_of_residence;
        this.password = password;
        this.accounts = accounts;
        this.loans = loans;
    }

    public static customer getByID(Connection connection, int id) {
        storedConnection = connection;
        customer instance = null;
        String query = "SELECT * FROM customer WHERE customer_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for userId
            preparedStatement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
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
                            account.getByCustomerID(connection, id),
                            loan.getByCustomerID(connection, id));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return instance;
    }

    @Override
    public String toString() {
        return "customer [customer_id=" + customer_id + ", first_name=" + first_name + ", last_name=" + last_name
                + ", date_of_birth=" + date_of_birth + ", nationality=" + nationality + ", nin=" + nin
                + ", last_login_date=" + last_login_date + ", email=" + email + ", phone_number=" + phone_number
                + ", address=" + address + ", city=" + city + ", state=" + state + ", postal_code=" + postal_code
                + ", country_of_residence=" + country_of_residence + ", password=" + password + ", accounts=" + accounts
                + ", loans=" + loans + "]";
    }

    // getter of account
    public ArrayList<account> getAccounts() {
        return accounts;
    }

    // getter of account array
    public account[] getAccountArray() {
        account[] accountArray = new account[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            accountArray[i] = accounts.get(i);
        }
        return accountArray;
    }

    public BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal(0);
        for (account acc : accounts) {
            balance = balance.add(acc.getBalance());
        }
        return balance.setScale(2, RoundingMode.HALF_UP);
    }

}
