package com.szachnowicz.exchange.dto;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Builder
public class PlnExchangeValue {
    private CurrencyCode currencyCode;
    private BigDecimal exchangeRatio;
    private ZonedDateTime exchangeDate;
}
