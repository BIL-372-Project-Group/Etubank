package db_sys;

import java.util.Date;

public class Card {
    private int cardId; // Primary Key
    private int accountId; // Foreign Key (references Account.account_id)
    private int cardTypeId; // Foreign Key (references Card_type.card_type_id)
    private String cardNumber; // Unique card number
    private double cardLimit; // Credit/debit limit
    private Date expiryDate; // Expiry date
    private int cvv; // Security code
    private int bankId; // Foreign Key (references Bank.bank_id)

    public Card(int cardId, int accountId, int cardTypeId, String cardNumber, double cardLimit,
                Date expiryDate, int cvv, int bankId) {
        this.cardId = cardId;
        this.accountId = accountId;
        this.cardTypeId = cardTypeId;
        this.cardNumber = cardNumber;
        this.cardLimit = cardLimit;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.bankId = bankId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(double cardLimit) {
        this.cardLimit = cardLimit;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", accountId=" + accountId +
                ", cardTypeId=" + cardTypeId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardLimit=" + cardLimit +
                ", expiryDate=" + expiryDate +
                ", cvv=" + cvv +
                ", bankId=" + bankId +
                '}';
    }
}
