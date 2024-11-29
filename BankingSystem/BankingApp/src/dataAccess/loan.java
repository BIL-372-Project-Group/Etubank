package dataAccess;

import java.util.Date;
import java.sql.Connection;

public class loan {
    int loan_id; // Primary Key
    int loan_type_id; // Foreign Key (references loan_type.loan_type_id)
    int customer_id; // Foreign Key (references customer.customer_id)
    int principal_amount; // Loan principal amount
    double interest_rate; // Interest rate
    int loan_status_id; // Foreign Key (references loan_status.loan_status_id)
    Date start_date; // Loan start date
    Date end_date; // Loan end date

    loan_type type;
    loan_status status;
    loan_payment_schedule paymentSchedule;
    
    public loan(int loan_id, int loan_type_id, int customer_id, int principal_amount, double interest_rate,
            int loan_status_id, Date start_date, Date end_date, loan_type type, loan_status status,
            loan_payment_schedule paymentSchedule) {
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
}
