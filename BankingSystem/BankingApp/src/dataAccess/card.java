package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class card {
    public int card_id; // Primary Key
    public int account_id; // Foreign Key (references Account.account_id)
    public int card_type_id; // Foreign Key (references Card_type.card_type_id)
    public String card_number; // Unique card number
    public BigDecimal card_limit; // Credit/debit limit
    public Date expiry_date; // Expiry date
    public int cvv; // Security code

    public card_type type;

    public card(int card_id, int account_id, int card_type_id, String card_number, BigDecimal card_limit, Date expiry_date,
            int cvv, card_type type) {
        this.card_id = card_id;
        this.account_id = account_id;
        this.card_type_id = card_type_id;
        this.card_number = card_number;
        this.card_limit = card_limit;
        this.expiry_date = expiry_date;
        this.cvv = cvv;
        this.type = type;
    }

    @Override
    public String toString() {
        return "card [card_id=" + card_id + ", account_id=" + account_id + ", card_type_id=" + card_type_id
                + ", card_number=" + card_number + ", card_limit=" + card_limit + ", expiry_date=" + expiry_date
                + ", cvv=" + cvv + ", type=" + type + "]";
    }

    public static ArrayList<card> getByAccountID(Connection connection, int aid) {

        ArrayList<card> list = new ArrayList<card>();

        String query = "SELECT * FROM card WHERE account_id = ?";

        try  {
            connection = DriverManager.getConnection(DataAccessLayer.DB_URL, DataAccessLayer.DB_USERNAME, DataAccessLayer.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Set the parameter for userId
            preparedStatement.setInt(1, aid);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Map the result set to a User object
                    int id = resultSet.getInt("card_id");
                    list.add(
                        new card(
                        id,
                        aid,
                        resultSet.getInt("card_type_id"),
                        resultSet.getString("card_number"),
                        resultSet.getBigDecimal("card_limit"),
                        resultSet.getDate("expiry_date"),
                        resultSet.getInt("cvv"),
                        card_type.getByID(connection, resultSet.getInt("card_type_id"))
                        )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return list;
    }

}
