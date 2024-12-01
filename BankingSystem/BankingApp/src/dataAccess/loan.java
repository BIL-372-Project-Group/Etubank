package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loan {
    public int loan_id; // Primary Key
    public int loan_type_id; // Foreign Key (references loan_type.loan_type_id)
    public int customer_id; // Foreign Key (references customer.customer_id)
    public int principal_amount; // Loan principal amount
    public double interest_rate; // Interest rate
    public int loan_status_id; // Foreign Key (references loan_status.loan_status_id)
    public Date start_date; // Loan start date
    public Date end_date; // Loan end date

    public loan_type type;
    public loan_status status;
    public ArrayList<loan_payment_schedule> paymentSchedule;
    
    public loan(int loan_id, int loan_type_id, int customer_id, int principal_amount, double interest_rate,
            int loan_status_id, Date start_date, Date end_date, loan_type type, loan_status status,
            ArrayList<loan_payment_schedule> paymentSchedule) {
        this.loan_id = loan_id;
        this.loan_type_id = loan_type_id;
        this.customer_id = customer_id;
        this.principal_amount = principal_amount;
        this.interest_rate = interest_rate;
        this.loan_status_id = loan_status_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.type = type;
        this.status = status;
        this.paymentSchedule = paymentSchedule;
    }

    public static ArrayList<loan> getByCustomerID(Connection connection, int cid) {

        ArrayList<loan> list = new ArrayList<loan>();

        String query = "SELECT * FROM loan WHERE customer_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Set the parameter for userId
            preparedStatement.setInt(1, cid);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Map the result set to a User object
                    int id = resultSet.getInt("loan_id");
                    list.add(
                        new loan(
                        id,
                        resultSet.getInt("loan_type_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("principal_amount"),
                        resultSet.getDouble("interest_rate"),
                        resultSet.getInt("loan_status_id"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        loan_type.getByID(connection, resultSet.getInt("loan_type_id")),
                        loan_status.getByID(connection, resultSet.getInt("loan_status_id")),
                        loan_payment_schedule.getByLoanID(connection,id)
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
        return "loan [loan_id=" + loan_id + ", loan_type_id=" + loan_type_id + ", customer_id=" + customer_id
                + ", principal_amount=" + principal_amount + ", interest_rate=" + interest_rate + ", loan_status_id="
                + loan_status_id + ", start_date=" + start_date + ", end_date=" + end_date + ", type=" + type
                + ", status=" + status + ", paymentSchedule=" + paymentSchedule + "]";
    }
}
