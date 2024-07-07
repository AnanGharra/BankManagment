package com.group.BankManager.beans;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;

    private Double balance;
    private String status;
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @OneToMany(mappedBy = "targetAccount")
    private List<Transaction> sourceTransactions;

    @OneToMany(mappedBy = "targetAccount")
    private List<Transaction> targetTransactions;

    @OneToMany(mappedBy = "account")
    private List<CurrencyExchange> currencyExchanges;


    // Default CTOR
    public Account() {
    }

    // Parameterized CTOR
    public Account(Double balance, String status, String accountType, Customer customer) {
        this.balance = balance;
        this.status = status;
        this.accountType = accountType;
        this.customer = customer;
    }

    public Long getAccountID() {
        return accountID;
    }

    public Double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public String getAccountType() {
        return accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Transaction> getSourceTransactions() {
        return sourceTransactions;
    }

    public List<Transaction> getTargetTransactions() {
        return targetTransactions;
    }

    public List<CurrencyExchange> getCurrencyExchanges() {
        return currencyExchanges;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setSourceTransactions(List<Transaction> sourceTransactions) {
        this.sourceTransactions = sourceTransactions;
    }

    public void setTargetTransactions(List<Transaction> targetTransactions) {
        this.targetTransactions = targetTransactions;
    }

    public void setCurrencyExchanges(List<CurrencyExchange> currencyExchanges) {
        this.currencyExchanges = currencyExchanges;
    }

    @Override
    public String toString(){
        return "Account{" +
                "AccountId=" + this.accountID +
                ", Balance=" + this.balance +
                ", Status='" + this.status + '\'' +
                ", AccountType='" + this.accountType + '\'' +
                ", CustomerId=" + this.customer.getCustomerID() +
                '}';
    }
}
