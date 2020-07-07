package com.szachnowicz.exchange.persitance.exchange;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

 interface ExchangePlnJpaRepo extends JpaRepository<PlnExchangeEntity, Long> {
    Optional<PlnExchangeEntity> findByCurrencyCode(CurrencyCode currencyCode);
}
