package guis;

import java.math.BigDecimal;

import dataAccess.loan;
import dataAccess.loan_payment;
// Loan class to encapsulate loan details
import dataAccess.loan_payment_schedule;

public class Loan {
    private loan instance;

    public Loan(loan a) {
        this.instance = a;
    }

    public int getRemainingBalance() {
        return this.instance.principal_amount;
    }

    public void pay(double amount) {
        this.instance.principal_amount -= amount;
    }

    public double getInterestRate() {
        return instance.interest_rate;
    }
    public int getInstallments(){
        return instance.installments;
    }
}