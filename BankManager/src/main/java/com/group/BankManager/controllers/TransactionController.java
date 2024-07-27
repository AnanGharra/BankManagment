package com.group.BankManager.controllers;


import com.group.BankManager.BL.TransactionBL;
import com.group.BankManager.beans.Transaction;
import com.group.BankManager.exceptions.AccountNotFoundException;
import com.group.BankManager.exceptions.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionBL transactionBL;


    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Transaction transaction){
        try {
            transactionBL.deposit(transaction);
            return ResponseEntity.ok("Deposit Successful");
        }
        catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Transaction transaction){
        try {
            transactionBL.withdraw(transaction);
            return ResponseEntity.ok("Withdraw Successful");
        }
        catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (InsufficientFundsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/balance/{id}")
    public ResponseEntity<?> checkBalance(@PathVariable Long id){
        try {
            double balance = transactionBL.checkBalance(id);
            return ResponseEntity.ok("Balance: " + balance);
        }
        catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
