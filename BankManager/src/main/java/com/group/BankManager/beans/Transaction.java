package com.group.BankManager.beans;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionID;

    private String transactionType;
    private Double amount;
    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "sourceAccountID")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "targetID", nullable = true)
    private Account targetAccount;

    // currencyFrom, currencyTo, exchangeRate, loanID


    // Default CTOR
    public Transaction() {
    }

    // Parameterized CTOR
    public Transaction(String transactionType, Double amount, Date date, String description,
                       Account sourceAccount, Account targetAccount){
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    @Override
    public String toString(){
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate=" + this.date +
                ", transactionAmount=" + this.amount +
                ", transactionDescription='" + this.description + '\'' +
                ", sourceAccount=" + sourceAccount +
                ", destinationAccount=" + this.targetAccount +
                '}';
    }
}
