package db_sys;

import java.util.Date;

public class Loan {
    private int loanId; // Primary Key
    private int loanTypeId; // Foreign Key (references loan_type.loan_type_id)
    private int customerId; // Foreign Key (references customer.customer_id)
    private int principalAmount; // Loan principal amount
    private double interestRate; // Interest rate
    private int loanStatusId; // Foreign Key (references loan_status.loan_status_id)
    private Date startDate; // Loan start date
    private Date endDate; // Loan end date
    private int bankId; // Foreign Key (references bank.bank_id)


    public Loan(int loanId, int loanTypeId, int customerId, int principalAmount, double interestRate,
                int loanStatusId, Date startDate, Date endDate, int bankId) {
        this.loanId = loanId;
        this.loanTypeId = loanTypeId;
        this.customerId = customerId;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.loanStatusId = loanStatusId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bankId = bankId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(int principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(int loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanTypeId=" + loanTypeId +
                ", customerId=" + customerId +
                ", principalAmount=" + principalAmount +
                ", interestRate=" + interestRate +
                ", loanStatusId=" + loanStatusId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", bankId=" + bankId +
                '}';
    }
}
