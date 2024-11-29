package dataAccess;

import java.util.Date;
import java.sql.Connection;

public class loan_payment {
    public int payment_id; // Primary Key
    public int schedule_id; // Foreign Key (references LoanPaymentSchedule.scheduleId)
    public double amount_paid; // Positive floating-point number for the payment amount
    public Date payment_date; // Date of payment
    
    public loan_payment(int payment_id, int schedule_id, double amount_paid, Date payment_date) {
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
}
