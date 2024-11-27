package db_sys;

import java.util.Date;

public class LoanPaymentSchedule {
    private int scheduleId; // Primary Key
    private int loanId; // Foreign Key (references loan.loan_id)
    private Date dueDate; // Scheduled due date for payment
    private double amountDue; // Positive floating-point number for amount due
    private boolean status; // Binary status (Paid or Unpaid)
    private int bankId; // Foreign Key (references bank.bank_id)


    public LoanPaymentSchedule(int scheduleId, int loanId, Date dueDate, double amountDue, boolean status, int bankId) {
        this.scheduleId = scheduleId;
        this.loanId = loanId;
        this.dueDate = dueDate;
        this.amountDue = amountDue;
        this.status = status;
        this.bankId = bankId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "LoanPaymentSchedule{" +
                "scheduleId=" + scheduleId +
                ", loanId=" + loanId +
                ", dueDate=" + dueDate +
                ", amountDue=" + amountDue +
                ", status=" + status +
                ", bankId=" + bankId +
                '}';
    }
}
