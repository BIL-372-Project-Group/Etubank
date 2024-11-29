package dataAccess;

import java.util.Date;
import java.sql.Connection;

public class loan_payment {
    int paymeny_id; // Primary Key
    int schedule_id; // Foreign Key (references LoanPaymentSchedule.scheduleId)
    double amount_paid; // Positive floating-point number for the payment amount
    Date payment_date; // Date of payment

    public loan_payment(Connection c, int id) {
        this.paymeny_id = id;
        this.schedule_id = schedule_id;
        this.amount_paid = amount_paid;
        this.payment_date = payment_date;
    }
}
