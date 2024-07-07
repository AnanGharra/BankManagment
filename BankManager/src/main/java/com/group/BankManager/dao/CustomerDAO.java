package com.group.BankManager.dao;

import com.group.BankManager.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
}
