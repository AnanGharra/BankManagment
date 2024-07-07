package com.group.BankManager.dao;

import com.group.BankManager.beans.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDAO extends JpaRepository<Loan, Long> {
}
