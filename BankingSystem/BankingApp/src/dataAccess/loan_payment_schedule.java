package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;

public class loan_payment_schedule {
    int schedule_id; // Primary Key
    int loan_id; // Foreign Key (references loan.loan_id)
    Date due_date; // Scheduled due date for payment
    double amount_due; // Positive floating-point number for amount due
    boolean status; // Binary status (Paid or Unpaid)

    ArrayList<loan_payment> payments;

    public loan_payment_schedule(int schedule_id, int loan_id, Date due_date, double amount_due, boolean status,
            ArrayList<loan_payment> payments) {
        this.schedule_id = schedule_id;
        this.loan_id = loan_id;
        this.due_date = due_date;
        this.amount_due = amount_due;
        this.status = status;
        this.payments = payments;
    }
}
