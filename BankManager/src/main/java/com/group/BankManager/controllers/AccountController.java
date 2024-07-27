package com.group.BankManager.controllers;

import com.group.BankManager.BL.AccountBL;
import com.group.BankManager.beans.Account;
import com.group.BankManager.exceptions.AccountNotFoundException;
import com.group.BankManager.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AccountController {

    @Autowired
    private AccountBL accountBL;


    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        try {
            accountBL.createAccount(account);
            return ResponseEntity.ok("Account Created Successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping("/limit/{id}")
    public ResponseEntity<?> limitAccount(@PathVariable Long id){
        try {
            accountBL.limitAccount(id);
            return ResponseEntity.ok("Account Limited Successfully");
        }
        catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping("/suspend/{id}")
    public ResponseEntity<?> suspendAccount(@PathVariable Long id){
        try {
            accountBL.suspendAccount(id);
            return ResponseEntity.ok("Account Suspended Successfully");
        }
        catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
