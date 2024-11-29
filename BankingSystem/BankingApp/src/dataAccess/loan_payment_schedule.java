package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;

public class loan_payment_schedule {
    public int schedule_id; // Primary Key
    public int loan_id; // Foreign Key (references loan.loan_id)
    public Date due_date; // Scheduled due date for payment
    public double amount_due; // Positive floating-point number for amount due
    public boolean status; // Binary status (Paid or Unpaid)

    public ArrayList<loan_payment> payments;

    public loan_payment_schedule(int schedule_id, int loan_id, Date due_date, double amount_due, boolean status,
            ArrayList<loan_payment> payments) {
        this.schedule_id = schedule_id;
        this.loan_id = loan_id;
        this.due_date = due_date;
        this.amount_due = amount_due;
        this.status = status;
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "loan_payment_schedule [schedule_id=" + schedule_id + ", loan_id=" + loan_id + ", due_date=" + due_date
                + ", amount_due=" + amount_due + ", status=" + status + ", payments=" + payments + "]";
    }
}
