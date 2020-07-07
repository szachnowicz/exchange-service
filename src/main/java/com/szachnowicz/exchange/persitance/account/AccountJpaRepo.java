package com.szachnowicz.exchange.persitance.account;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface AccountJpaRepo extends JpaRepository<CurrencyAccountEntity, Long> {

    Optional<CurrencyAccountEntity> findByUserIdAndCurrencyCode(Long userId, CurrencyCode currencyCode);

    List<CurrencyAccountEntity> findAllByUserId(Long userId);

    Optional<CurrencyAccountEntity> findByAccountNumber(String accountNumber);

}
