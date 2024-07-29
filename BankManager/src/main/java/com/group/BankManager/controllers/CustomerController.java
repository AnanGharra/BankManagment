package com.group.BankManager.controllers;


import com.group.BankManager.BL.CustomerBL;
import com.group.BankManager.beans.Customer;
import com.group.BankManager.dao.CustomerDAO;
import com.group.BankManager.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
    private CustomerBL customerBL;

    @Autowired
    private CustomerDAO customerDAO; //to edit

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerDAO.findAll();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build(); // No content response if empty
        }
        return ResponseEntity.ok(customers); // Return list of customers
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        try{
            customerBL.addCustomer(customer);
            return ResponseEntity.ok("Customer Added Successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        try{
            customerBL.deleteCustomer(id);
            return ResponseEntity.ok("Customer Deleted Successfully");
        }
        catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        try{
            customerBL.updateCustomer(customer);
            return ResponseEntity.ok("Customer Updated Successfully");
        }
        catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
