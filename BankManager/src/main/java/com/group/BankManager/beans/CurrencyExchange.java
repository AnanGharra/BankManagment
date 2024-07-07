package com.group.BankManager.beans;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchangeID;

    private String currencyFrom;
    private String currencyTo;
    private Double exchangeRate;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "accountID")
    private Account account;


    //CTOR, SETTERS, GETTERS

    // Default CTOR
    public CurrencyExchange() {
    }

    // Parameterized CTOR
    public CurrencyExchange(Account account, Date date, Double exchangeRate, String currencyTo, String currencyFrom) {
        this.account = account;
        this.date = date;
        this.exchangeRate = exchangeRate;
        this.currencyTo = currencyTo;
        this.currencyFrom = currencyFrom;
    }

    public void setExchangeID(Long exchangeID) {
        this.exchangeID = exchangeID;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
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
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
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
}
