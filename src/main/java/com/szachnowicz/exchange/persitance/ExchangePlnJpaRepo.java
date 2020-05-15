package com.szachnowicz.exchange.persitance;

import com.szachnowicz.exchange.domian.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

 interface ExchangePlnJpaRepo extends JpaRepository<PlnExchangeEntity, Long> {
    Optional<PlnExchangeEntity> findByCurrencyCode(CurrencyCode currencyCode);
}
