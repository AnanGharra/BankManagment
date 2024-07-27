package com.group.BankManager.controllers;


import com.group.BankManager.BL.LoanBL;
import com.group.BankManager.beans.Loan;
import com.group.BankManager.exceptions.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanBL loanBL;


    @PostMapping("/grant")
    public ResponseEntity<?> grantLoan(@RequestBody Loan loan){
        try {
            loanBL.grantLoan(loan);
            return ResponseEntity.ok("Loan Granted Successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/repay")
    public ResponseEntity<?> repayLoan(@RequestBody Loan loan){
        try {
            loanBL.repayLoan(loan);
            return ResponseEntity.ok("Loan Repaid Successfully");
        }
        catch (LoanNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
