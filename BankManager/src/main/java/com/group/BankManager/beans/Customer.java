package com.group.BankManager.beans;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String username;
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToMany(mappedBy = "customer")
    private List<Loan> loans;

    // Default CTOR
    public Customer() {
    }

    // Parameterized CTOR
    public Customer(String firstName, String lastName, String address, String email, String phone, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public Customer(long customerID, String firstName, String lastName, String email, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;

    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "CustomerID=" + this.customerID +
                ", FirstName='" + this.firstName + '\'' +
                ", LastName='" + this.lastName + '\'' +
                ", Email='" + this.email + '\'' +
                ", PhoneNum=" + this.phone +
                ", Address='" + this.address + '\'' +
                '}';
    }
}
