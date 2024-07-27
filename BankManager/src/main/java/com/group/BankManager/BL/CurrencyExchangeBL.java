package com.group.BankManager.BL;


import com.group.BankManager.beans.Account;
import com.group.BankManager.beans.CurrencyExchange;
import com.group.BankManager.dao.AccountDAO;
import com.group.BankManager.dao.CurrencyExchangeDAO;
import com.group.BankManager.exceptions.AccountNotFoundException;
import com.group.BankManager.exceptions.CurrencyExchangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyExchangeBL {

    @Autowired
    private CurrencyExchangeDAO currencyExchangeDAO;

    @Autowired
    private AccountDAO accountDAO;


    public List<CurrencyExchange> getRates(){
        return currencyExchangeDAO.findAll();
    }


    public void exchangeCurrency(CurrencyExchange currencyExchange) throws CurrencyExchangeException, AccountNotFoundException {
        Optional<CurrencyExchange> rate = currencyExchangeDAO.findByFromCurrencyAndToCurrency(currencyExchange.getCurrencyFrom(), currencyExchange.getCurrencyTo());

        if(rate.isEmpty()){
            throw new CurrencyExchangeException("Exchange rate not found for the specified currencies");
        }

        double exchangeRate = rate.get().getExchangeRate();

        Account fromAccount = accountDAO.findById(currencyExchange.getAccount().getAccountID())
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        double amountInToCurrency = currencyExchange.getAmount() * exchangeRate;

        fromAccount.setBalance(fromAccount.getBalance() - currencyExchange.getAmount());
        accountDAO.save(fromAccount);

        Account toAccount = accountDAO.findById(currencyExchange.getAccount().getAccountID())
                .orElseThrow(() -> new AccountNotFoundException("Destination Account Not Found"));

        toAccount.setBalance(toAccount.getBalance() + amountInToCurrency);
        accountDAO.save(toAccount);
        currencyExchange.setExchangeRate(exchangeRate);
        currencyExchangeDAO.save(currencyExchange);
    }
}
