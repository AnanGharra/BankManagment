package com.group.BankManager.BL;


import com.group.BankManager.beans.Account;
import com.group.BankManager.dao.AccountDAO;
import com.group.BankManager.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBL {

    @Autowired
    private AccountDAO accountDAO;


    public void createAccount(Account account){
        accountDAO.save(account);
    }


    public void limitAccount(Long id) throws AccountNotFoundException {
        Account account = accountDAO.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
        account.setStatus("Limited");
        accountDAO.save(account);
    }


    public void suspendAccount(Long id) throws AccountNotFoundException {
        Account account = accountDAO.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
        account.setStatus("Suspended");
        accountDAO.save(account);
    }
}
