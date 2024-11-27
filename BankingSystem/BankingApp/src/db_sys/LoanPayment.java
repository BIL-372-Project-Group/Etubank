package db_sys;

import java.util.Date;

public class LoanPayment {
    private int paymentId; // Primary Key
    private int scheduleId; // Foreign Key (references LoanPaymentSchedule.scheduleId)
    private double amountPaid; // Positive floating-point number for the payment amount
    private Date paymentDate; // Date of payment
    private int bankId; // Foreign Key (references Bank.bankId)



    // Parameterized constructor
    public LoanPayment(int paymentId, int scheduleId, double amountPaid, Date paymentDate, int bankId) {
        this.paymentId = paymentId;
        this.scheduleId = scheduleId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.bankId = bankId;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "LoanPayment{" +
                "paymentId=" + paymentId +
                ", scheduleId=" + scheduleId +
                ", amountPaid=" + amountPaid +
                ", paymentDate=" + paymentDate +
                ", bankId=" + bankId +
                '}';
    }
}
