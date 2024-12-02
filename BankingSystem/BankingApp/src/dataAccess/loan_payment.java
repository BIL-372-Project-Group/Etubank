package dataAccess;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loan_payment {
    public int payment_id; // Primary Key
    public int schedule_id; // Foreign Key (references LoanPaymentSchedule.scheduleId)
    public BigDecimal amount_paid; // Positive floating-point number for the payment amount
    public Date payment_date; // Date of payment
    
    public loan_payment(int payment_id, int schedule_id, BigDecimal amount_paid, Date payment_date) {
        this.payment_id = payment_id;
        this.schedule_id = schedule_id;
        this.amount_paid = amount_paid;
        this.payment_date = payment_date;
    }

    @Override
    public String toString() {
        return "loan_payment [payment_id=" + payment_id + ", schedule_id=" + schedule_id + ", amount_paid="
                + amount_paid + ", payment_date=" + payment_date + "]";
    }

    public static loan_payment getByScheduleID(Connection connection, int sid) {

        loan_payment instance = null;

        String query = "SELECT * FROM loan_payment WHERE schedule_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Set the parameter for userId
            preparedStatement.setInt(1, sid);
            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set
                    int id = resultSet.getInt("payment_id");
                    instance = new loan_payment(
                            id,
                            sid,
                            resultSet.getBigDecimal("amount_paid"),
                            resultSet.getDate("payment_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        return instance;
    }
}
