package com.group.BankManager.dao;

import com.group.BankManager.beans.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<Transaction, Long> {
}
