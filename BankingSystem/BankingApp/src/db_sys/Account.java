package db_sys;

import java.util.Date;

public class Account {
    private int accountId; // Primary Key
    private int customerId; // Foreign Key (references Customer.customer_id)
    private String iban; // International Bank Account Number
    private String accountNumber; // Unique account number for transactions
    private int accountStatusId; // Foreign Key (references Account_status.account_status_id)
    private int accountTypeId; // Foreign Key (references Account_type.account_type_id)
    private Date dateOpened; // Date account was opened
    private Date lastActivityDate; // Last activity date
    private double balance; // Current account balance
    private String currency; // Currency type (e.g., USD, EUR)
    private int bankId; // Foreign Key (references Bank.bank_id)



    public Account(int accountId, int customerId, String iban, String accountNumber, int accountStatusId,
                   int accountTypeId, Date dateOpened, Date lastActivityDate, double balance, String currency, int bankId) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.iban = iban;
        this.accountNumber = accountNumber;
        this.accountStatusId = accountStatusId;
        this.accountTypeId = accountTypeId;
        this.dateOpened = dateOpened;
        this.lastActivityDate = lastActivityDate;
        this.balance = balance;
        this.currency = currency;
        this.bankId = bankId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(int accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", iban='" + iban + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountStatusId=" + accountStatusId +
                ", accountTypeId=" + accountTypeId +
                ", dateOpened=" + dateOpened +
                ", lastActivityDate=" + lastActivityDate +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", bankId=" + bankId +
                '}';
    }
}
