package com.group.BankManager.controllers;


import com.group.BankManager.BL.CurrencyExchangeBL;
import com.group.BankManager.beans.CurrencyExchange;
import com.group.BankManager.exceptions.CurrencyExchangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeBL currencyExchangeBL;


    @GetMapping("/rates")
    public ResponseEntity<?> getRates(){
        try {
            List<CurrencyExchange> rates = currencyExchangeBL.getRates();
            return ResponseEntity.ok(rates);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/exchange")
    public ResponseEntity<?> exchangeCurrency(@RequestBody CurrencyExchange currencyExchange){
        try {
            currencyExchangeBL.exchangeCurrency(currencyExchange);
            return ResponseEntity.ok("Currency Exchanged Successfully");
        }
        catch (CurrencyExchangeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
