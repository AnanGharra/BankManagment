package com.group.BankManager.beans;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchangeID;

    private String fromCurrency;
    private String toCurrency;
    private Double exchangeRate;
    private Date date;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "accountID")
    private Account account;


    //CTOR, SETTERS, GETTERS

    // Default CTOR
    public CurrencyExchange() {
    }

    // Parameterized CTOR
    public CurrencyExchange(Account account, Date date, Double exchangeRate, String toCurrency, String fromCurrency, Double amount) {
        this.account = account;
        this.date = date;
        this.exchangeRate = exchangeRate;
        this.toCurrency = toCurrency;
        this.fromCurrency = fromCurrency;
        this.amount = amount;
    }

    public void setExchangeID(Long exchangeID) {
        this.exchangeID = exchangeID;
    }

    public void setCurrencyFrom(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public void setCurrencyTo(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getExchangeID() {
        return exchangeID;
    }

    public String getCurrencyFrom() {
        return fromCurrency;
    }

    public String getCurrencyTo() {
        return toCurrency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Date getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
