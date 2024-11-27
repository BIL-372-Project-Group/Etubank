package db_sys;

import java.util.Date;

public class TransactionHistory {
    private int historyId; // Primary Key
    private int accountId; // Foreign Key (references account.account_id)
    private int transactionId; // Foreign Key (references transaction.transaction_id)
    private Date transactionDate; // Date of the transaction
    private double balanceAfterTransaction; // Account balance after the transaction
    private int bankId; // Foreign Key (references bank.bank_id)

    public TransactionHistory(int historyId, int accountId, int transactionId, Date transactionDate,
                               double balanceAfterTransaction, int bankId) {
        this.historyId = historyId;
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.bankId = bankId;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "historyId=" + historyId +
                ", accountId=" + accountId +
                ", transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                ", bankId=" + bankId +
                '}';
    }
}
