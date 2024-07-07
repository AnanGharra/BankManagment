package com.group.BankManager.dao;

import com.group.BankManager.beans.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeDAO extends JpaRepository<CurrencyExchange, Long> {
}
