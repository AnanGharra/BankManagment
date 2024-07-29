package com.group.BankManager.BL;


import com.group.BankManager.beans.Loan;
import com.group.BankManager.dao.LoanDAO;
import com.group.BankManager.exceptions.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBL {

    @Autowired
    private LoanDAO loanDAO;

    public void grantLoan(Loan loan){
        loanDAO.save(loan);
        //update balance
    }


    public void repayLoan(Loan loan) throws LoanNotFoundException {
        Loan existingLoan = loanDAO.findById(loan.getCustomer().getCustomerID()).orElseThrow(() -> new LoanNotFoundException("Loan Not Found!"));
        existingLoan.setRemainingAmount(existingLoan.getRemainingAmount() - existingLoan.getMonthlyRepayment());
        loanDAO.save(existingLoan);
        //update balance
    }
}
