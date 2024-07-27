package com.group.BankManager.BL;

import com.group.BankManager.beans.Account;
import com.group.BankManager.beans.Transaction;
import com.group.BankManager.dao.AccountDAO;
import com.group.BankManager.dao.TransactionDAO;
import com.group.BankManager.exceptions.AccountNotFoundException;
import com.group.BankManager.exceptions.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionBL {

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private AccountDAO accountDAO;


    public void deposit(Transaction transaction) throws AccountNotFoundException {
        Account account = accountDAO.findById(transaction.getTargetAccount().getAccountID()).orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
        account.setBalance(account.getBalance() + transaction.getAmount());
        transactionDAO.save(transaction);
        accountDAO.save(account);
    }


    public void withdraw(Transaction transaction) throws AccountNotFoundException, InsufficientFundsException {
        Account account = accountDAO.findById(transaction.getTargetAccount().getAccountID()).orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
        if(account.getBalance() < transaction.getAmount()){
            throw new InsufficientFundsException("Insufficient Funds!");
        }
        account.setBalance(account.getBalance() - transaction.getAmount());
        transactionDAO.save(transaction);
        accountDAO.save(account);
    }


    public double checkBalance(Long accoundId) throws AccountNotFoundException {
        Account account = accountDAO.findById(accoundId).orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
        return account.getBalance();
    }

}
