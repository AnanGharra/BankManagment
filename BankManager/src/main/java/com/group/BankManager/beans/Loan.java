package com.group.BankManager.beans;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanID;

    private Double amount;
    private Double interestRate;
    private Date startDate;
    private Date endDate;
    private Double monthlyRepayment;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;


    //CTOR, SETTERS, GETTERS


    // Default CTOR
    public Loan() {
    }

    // Parameterized CTOR
    public Loan(Double amount, Double interestRate, Date startDate, Date endDate, Double monthlyRepayment, Customer customer) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRepayment = monthlyRepayment;
        this.customer = customer;
    }

    public Long getLoanID() {
        return loanID;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Calculates the total repayment per year
    public Double getTotalRepaymentPerYear(){
        return this.monthlyRepayment * 12;
    }

    @Override
    public String toString(){
        return "Loan{" +
                "loanID=" + this.loanID +
                ", Amount=" +  + this.amount +
                ", InterestRate=" + this.interestRate +
                ", StartDate=" + this.startDate +
                ", EndDate=" + this.endDate +
                ", monthlyRepayment=" + monthlyRepayment +
                ", CustomerID=" + this.customer.getCustomerID() +
                '}';
    }

}
