package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loan_payment_schedule {
    public int schedule_id; // Primary Key
    public int loan_id; // Foreign Key (references loan.loan_id)
    public Date due_date; // Scheduled due date for payment
    public BigDecimal amount_due; // Positive floating-point number for amount due
    public boolean status; // Binary status (Paid or Unpaid)

    public loan_payment payment;

    public loan_payment_schedule(int schedule_id, int loan_id, Date due_date, BigDecimal amount_due, boolean status,
            loan_payment payment) {
        this.schedule_id = schedule_id;
        this.loan_id = loan_id;
        this.due_date = due_date;
        this.amount_due = amount_due;
        this.status = status;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "loan_payment_schedule [schedule_id=" + schedule_id + ", loan_id=" + loan_id + ", due_date=" + due_date
                + ", amount_due=" + amount_due + ", status=" + status + ", payment=" + payment + "]";
    }

    public static ArrayList<loan_payment_schedule> getByLoanID(Connection connection, int lid) {

        ArrayList<loan_payment_schedule> list = new ArrayList<loan_payment_schedule>();

        String query = "SELECT * FROM loan_payment_schedule WHERE loan_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for userId
            preparedStatement.setInt(1, lid);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Map the result set to a User object
                    int id = resultSet.getInt("schedule_id");
                    list.add(
                        new loan_payment_schedule(
                            id,
                            lid,
                            resultSet.getDate("due_date"),
                            resultSet.getBigDecimal("amount_due"),
                            resultSet.getBoolean("status"),
                            loan_payment.getByScheduleID(connection, id)
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
