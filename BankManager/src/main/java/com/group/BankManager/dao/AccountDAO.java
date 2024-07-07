package com.group.BankManager.dao;

import com.group.BankManager.beans.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account, Long> {

}
