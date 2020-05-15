package com.szachnowicz.exchange.persitance;

import com.szachnowicz.exchange.domian.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "pln_exchange")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class PlnExchangeEntity {

    @Id
    @GeneratedValue
    protected Long id;
    @Enumerated
    private CurrencyCode currencyCode;
    private BigDecimal exchangeRatio;
    private ZonedDateTime exchangeDate;
}
