package com.group.BankManager.dao;

import com.group.BankManager.beans.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeDAO extends JpaRepository<CurrencyExchange, Long> {
    Optional<CurrencyExchange> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
